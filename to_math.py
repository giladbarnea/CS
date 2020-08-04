#!/usr/bin/env python3

import sys
from pathlib import Path
from md2pdf import md2pdf
import click
import re
from ipdb import set_trace, launch_ipdb_on_exception
from functools import partial

sys.breakpointhook = partial(set_trace, context=30)


# sys.excepthook = lambda *args, **kwargs: print(args, kwargs) or sys.breakpointhook()


def escape(pair: tuple):
    if isinstance(pair[0], str):
        return '\\' + pair[0].strip(), pair[0].strip()
    return pair


def get_pairs():
    old_new_pairs = [
        # (re.compile(r'(not )(\([\w\W]* [\w\W]* [\w\W]*[^)]*)', re.DOTALL),
        #  lambda match: f'<span style="text-decoration: overline">{match.group(2)}</span>'),
        (' and ', ' ∧ '),
        (' AND ', ' ∧ '),
        (' !in ', ' ∉ '),
        (' !IN ', ' ∉ '),
        (' in ', ' ∈ '),
        (' IN ', ' ∈ '),
        (' union ', ' ∪ '),
        (' uin ', ' ∪ '),
        (' UNION ', ' ∪ '),
        (' UIN ', ' ∪ '),
        (' intersection ', ' ∩ '),
        (' intersect ', ' ∩ '),
        (' isct ', ' ∩ '),
        (' INTERSECTION ', ' ∩ '),
        (' INTERSECT ', ' ∩ '),
        (' ISCT ', ' ∩ '),
        (' not ', ' ¬ '),
        (' NOT ', ' ¬ '),
        (' or ', ' ∨ '),
        (' OR ', ' ∨ '),
        ('All ', '∀ '),
        ('ALL ', '∀ '),
        ('Exists ', '∃ '),
        ('EXISTS ', '∃ '),
        (' equiv', ' ≡ '),
        (re.compile(r'(?<!\*)\*(?!\*)'), '·'),  # mult
        (' EQUIV', ' ≡ '),
        (' <=> ', ' ⇔ '),
        (' <-> ', ' ↔ '),
        (' v ', ' ∨ '),
        ('&', 'Λ'),
        ('0', '∅'),
        ('->', '→'),
        ('=>', '⇒'),
        ('!<=', '⊈'),
        ('<=', '⊆'),
        ('!<', '⊄'),
        (re.compile(r'(?<!\\)<(?!(span|/|strike|br|div))'), '⊂'),
        (' u ', ' ∪ '),
        (' n ', ' ∩ '),
        (re.compile(r'(?<![-\w])-(?![- ])'), '¬'),
        ('~', '¬'),
        ('!=', '≠'),
        
        ]
    keys = set()
    symbols = set()
    escaped = []  # order matters
    for key, symbol in filter(lambda pair: isinstance(pair[0], str), old_new_pairs):
        stripped_key = key.strip()
        stripped_symbol = symbol.strip()
        keys.add(stripped_key if len(stripped_key) == 1 else f'({stripped_key})')
        symbols.add(stripped_symbol if len(stripped_symbol) == 1 else f'({stripped_symbol})')
        escaped.append(('\\' + stripped_key, stripped_key))
    allowed = re.escape(''.join(list(keys) + list(symbols)))
    # breakpoint()
    complement = (re.compile(fr'(not )(\([\w{allowed}]* [\w{allowed}]* [\w{allowed}]*)'),
                  lambda match: f'<span style="text-decoration: overline">{match.group(2)}</span>')
    # escaped = list(map(escape, old_new_pairs))
    return [complement] + old_new_pairs + escaped


def replace_values(old_new_pairs: list, text):
    replaced = text
    for old, new in old_new_pairs:
        if isinstance(old, re.Pattern):
            replaced = re.sub(old, new, replaced)
        else:  # str
            replaced = replaced.replace(old, new)
    
    return replaced


def replace_and_copy(text: str):
    from pyperclip import copy
    replaced = replace_values(get_pairs(), text)
    copy(replaced)
    print(f'copied {len(replaced)} chars successfully')
    return replaced


def printhelp():
    newline = '\n'
    print(f"""
to_math.py
    clipboard in/out. also prints result


to_math.py path/to/file.md [--out=<OUT> [--css=<STYLE.CSS>]] [--keepmath]
    backs up file to .backup, writes modified contents into <OUT> (or just prints if <OUT> is omitted)
    always prints result unless <OUT> is an actual file
    if --keepmath, then %math /%math tags are not removed from result.
    
    --out=path/to/outfile.pdf
    --out=clipboard


to_math.py -h, --help
    this message

{newline.join(map(repr, get_pairs()))}
        """)


def from_file(infile: Path, outfile: Path, css: str = None, keepmath=False):
    if infile == outfile and input(f'infile and outfile are the same ({infile}), continue? y/n\t') != 'y':
        sys.exit('aborting')
    with open(infile) as f:
        text = f.read()
    with open(infile.with_suffix('.backup'), mode='w') as f:
        f.write(text)
    print(f'backed up to {infile.with_suffix(".backup")}')
    
    pairs = get_pairs()
    
    text = replace_values([
        
        (re.compile(r'(?<=<box>\n)[\w\W]*(?=</box>)', re.DOTALL),
         lambda match: match.group().replace('\n', '<br>')),
        # inline comment:
        (re.compile(r'(?<!\n)// [^\n]*'), lambda match: f'<span style="padding-left: 25pt; color: rgb(75,75,75)">{match.group()}</span>'),
        
        # comment in its own line
        (re.compile(r'(?<=\n)// [^\n]*'), lambda match: f'<span style="color: rgb(75,75,75)">{match.group()}</span>'),
        
        ('\n\n\n\n', '\n<br><br>\n'),
        ('\n\n\n', '\n<br>\n'),
        ('<box>', '<div class="box">'),
        ('</box>', '</div>'),
        ('<thin>', '<div class="thin-line"></div>'),
        ('<line>', '<div class="line"></div>'),
        
        (re.compile('\n'), '\n\n'),
        ], text)
    lines = text.splitlines()
    
    # lines = list(map(lambda s: s + '\n' if s.endswith('\n') and s != '\n' else s, lines))
    # replaced = []
    # if infile.suffix == '.md':
    #     for line in lines:
    #         if line == '\n':
    #             # do nothing for just line breaks
    #             replaced.append(line)
    #         else:
    #             replaced.append(line + '\n')
    
    replaced = []
    is_math = False
    for line in lines:
        # if not line.strip():
        #     breakpoint()
        if is_math:
            if line.strip() == '/%math':
                # can only happen toggled is_math=True
                is_math = False
                if keepmath:
                    replaced.append(line)
                continue
            
            # it's math
            replaced.append(replace_values(pairs, line))
            continue
        
        # is_math = False
        if line.strip() == '%math':
            is_math = True
            if keepmath:
                replaced.append(line)
            continue
        
        # not math, append as is
        replaced.append(line)
    
    if outfile.suffix == '.pdf':
        md2pdf(str(outfile), md_content='\n'.join(replaced), css_file_path=css)
    else:
        with open(outfile, mode='w') as f:
            f.write('\n'.join(replaced))
    print(f'wrote modified content of {infile} into {outfile}')


@click.command()
@click.argument('file', required=False)
@click.option('-h', '--help', is_flag=True, required=False)
@click.option('-o', '--out', required=False)
@click.option('--css', required=False)
@click.option('--keepmath', is_flag=True, required=False)
def main(file=None, help=False, out=None, css=None, keepmath=False):
    if help is True:
        printhelp()
        return
    if file is not None:
        file = Path(file)
        if file.is_file():
            if out is None:
                out = file
            else:
                out = Path(out)
            from_file(file, out, css, keepmath)
            return
        else:
            sys.exit(f'{file} is not a file, aborting')
    # no args
    from pyperclip import paste
    replace_and_copy(paste())


if __name__ == "__main__":
    with launch_ipdb_on_exception():
        main()
