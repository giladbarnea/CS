#!/usr/bin/env python3

import sys
from pathlib import Path
from typing import Union, Tuple, List
from md2pdf import md2pdf
import click
import re
# from ipdb import set_trace, launch_ipdb_on_exception
from functools import partial


# sys.breakpointhook = partial(set_trace, context=30)


# sys.excepthook = lambda *args, **kwargs: print(args, kwargs) or sys.breakpointhook()


def _get_pairs() -> List[Tuple]:
    # https://www.wikiwand.com/en/Mathematical_operators_and_symbols_in_Unicode
    old_new_pairs = [
        # case insensitive pairs first
        (' and ', ' ∧ '),
        (' !in ', ' ∉ '),
        (' in ', ' ∈ '),
        (' union ', ' ∪ '),
        (' uin ', ' ∪ '),
        (' intersection ', ' ∩ '),
        (' intersect ', ' ∩ '),
        (' isct ', ' ∩ '),
        (' not ', ' ¬ '),
        (' or ', ' ∨ '),
        ('all ', '∀ '),
        ('exists ', '∃ '),
        (re.compile(' (sd|sdiff) '), ' Δ '),
        (re.compile(r' equiv ?\b'), ' ≡ '),
        (re.compile(r'(?<!\*)\*(?!\*)'), '·'),  # mult
        (' EQUIV', ' ≡ '),
        (' <=> ', ' ⇔ '),
        (' <-> ', ' ↔ '),
        (' iff ', ' ⟺ '),
        
        ('&', '∧'),
        (re.compile(r'(?<!\d)0(?<!\d)'), '∅'),
        ('==>', '⟹'),
        ('->', '→'),  # ⟶?
        ('=>', '⇒'),
        ('!<=', '⊈'),
        ('<=', '⊆'),
        ('!<', '⊄'),
        
        # ignore opening / closing tags
        (re.compile(r'(?<!\\)<(?!([a-z]|/))'), '⊂'),
        
        (re.compile(r'(?<![-\w])-(?![- ])'), '¬'),
        (re.compile(r'(?<=[A-Z)])(?<= )?(x|cp)(?= )?(?=[A-Z(])'), '×'),  # cartesian prod
        (re.compile(r'(?<=[A-Z])(?<= )?(-)(?= )?(?=[A-Z])'), '−'),  # minus
        ('~', '¬'),
        ('!=', '≠'),
        ('!=', '≠'),
        (' inf ', '∞'),
        (' sqr ', '√'),
        (re.compile(r'(?<= )(powerset|pset|P)(?= )?(?=(\())'), '𝓟'),
        # (re.compile(r'<(?=\w)'), '⟨'),
        # (re.compile(r'(?<=\w)>'), '⟩'),
        
        (re.compile(r'(?<=[A-Z\W])(?<= )?(u)(?= )?(?=[A-Z\W])'), ' ∪ '),
        (re.compile(r'(?<=[A-Z\W])(?<= )?(n)(?= )?(?=[A-Z\W])'), '∩'),
        
        (re.compile(r'(?<=[A-Z\W])(?<= )?(v)(?= )?(?=[A-Z\W])'), '∨'),
        
        ]
    # add uppercase form
    old_new_pairs += [(key, symbol) if isinstance(key, re.Pattern) else (key.upper(), symbol) for key, symbol in old_new_pairs]
    
    # case sensitive pairs todo: consider doing like ×
    old_new_pairs += [
        
        ]
    keys = set()
    symbols = set()
    escaped = []  # order matters
    # for key, symbol in filter(lambda pair: isinstance(pair[0], str), old_new_pairs):
    for key, symbol in old_new_pairs:
        # populate `escaped` list with keys of pairs that are strings,
        # because hopefully regex pairs are specific enough to not need a way to escape
        # `escaped` list becomes part of returned pairs
        # `symbols` and `keys` sets are joined into `allowed` str for `complement` pair
        
        try:
            stripped_key = key.strip()
        except AttributeError as e:
            pass  # re.Pattern
        else:
            # "u" → "u", but "sd" → "(sd)". NOTE: () aren't literal, they're interpreted by reg
            keys.add(stripped_key if len(stripped_key) == 1 else f'({stripped_key})')
            escaped.append(('\\' + stripped_key, stripped_key))
        
        try:
            stripped_symbol = symbol.strip()
        except AttributeError as e:
            pass
        else:
            symbols.add(stripped_symbol if len(stripped_symbol) == 1 else f'({stripped_symbol})')
    
    allowed: str = re.escape(''.join(keys.union(symbols)))
    
    # minus in the middle for A - B
    binary_rel = rf'[\w{allowed}]+ [\w{allowed}-]+ [\w{allowed}]+'
    # i.e. "not (A or B)", "not (A ∪ B)", "not (~A ∪ ∅)"
    # whitespace is not optional to prevent false positives
    complement = (re.compile(fr'(not )(\({binary_rel}\))'),
                  lambda match: f'<oline>{match.group(2)}</oline>')
    
    return [complement] + old_new_pairs + escaped


CHAR_MATH_PAIRS = _get_pairs()
for arg in sys.argv[1:]:
    if arg == '-y':
        NO_CONFIRM = True
        sys.argv.remove(arg)
        break
else:
    NO_CONFIRM = False


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
    replaced = replace_values(CHAR_MATH_PAIRS, text)
    copy(replaced)
    print(f'copied {len(replaced)} chars successfully')
    return replaced


def printhelp():
    newline = '\n'
    print(f"""
to_math.py
    clipboard in/out. also prints result


to_math.py path/to/file.md [--out=<OUT> [--css=<STYLE.CSS>]] [--keepmath] [--math-everywhere] [-y]
    backs up file to .backup, writes modified contents into <OUT> (or just prints if <OUT> is omitted)
    always prints result unless <OUT> is an actual file
    
    if --keepmath, then %math /%math tags are not removed from result.
    if --math-everywhere, then everything is treated as if inside %math /%math tags.
    if -y, don't confirm when in and out files are the same.
    
    --out=path/to/outfile.pdf [--css=path/to/style.css]

to_math.py -h, --help
    this message

{newline.join(map(repr, CHAR_MATH_PAIRS))}
        """)


def from_file(infile: Path, outfile: Path, css: str = None, keepmath=False, math_everywhere=True):
    if infile == outfile and not NO_CONFIRM and input(f'infile and outfile are the same ({infile}), continue? y/n\t') != 'y':
        sys.exit('aborting')
    with open(infile) as f:
        text = f.read()
    with open(infile.with_suffix('.backup'), mode='w') as f:
        f.write(text)
    print(f'backed up to {infile.with_suffix(".backup")}')
    
    if infile.suffix == '.md':
        text = replace_values([
            
            (re.compile(r'(?<=<box>\n)[\w\W]*(?=</box>)', re.DOTALL),
             lambda match: match.group().replace('\n', '<br>')),
            
            # inline comment:
            (re.compile(r'(?<!\n)// [^\n]*'), lambda match: f'<comment>{match.group()}</comment>'),
            
            # comment in its own line
            (re.compile(r'(?<=\n)// [^\n]*'), lambda match: f'<grey>{match.group()}</grey>'),
            
            ('\n\n\n\n', '\n<br><br>\n'),
            ('\n\n\n', '\n<br>\n'),
            
            # uncomment if css box {} doesn't work and needs to be converted to div.box
            ('<box>', '<div class="box">'),
            ('</box>', '</div>'),
            ('<thin>', '<div class="thin-line"></div>'),
            ('<line>', '<div class="line"></div>'),
            
            # exclude if last char in line is pipe == markdown table
            # this needs to be located after all other '\n' modifications
            (re.compile(r'(?<!\|)\n'), '\n\n'),
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
    
    # we want is_math = False if not math_everywhere, otherwise we want is_math = True all the time 
    is_math = math_everywhere
    for line in lines:
        # if not line.strip():
        #     breakpoint()
        if is_math:
            if line.strip() == '/%math':
                # can only happen toggled is_math=True
                # we want is_math = False if not math_everywhere, otherwise we want is_math = True all the time 
                is_math = math_everywhere
                if keepmath:
                    replaced.append(line)
                continue
            
            # it's math
            replaced.append(replace_values(CHAR_MATH_PAIRS, line))
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
@click.option('--math-everywhere', is_flag=True, required=False)
def main(file=None, help=False, out=None, css=None, keepmath=False, math_everywhere=False):
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
            from_file(file, out, css, keepmath, math_everywhere)
            return
        else:
            sys.exit(f'{file} is not a file, aborting')
    # no args
    from pyperclip import paste
    replace_and_copy(paste())


if __name__ == "__main__":
    main()
    # with launch_ipdb_on_exception():
