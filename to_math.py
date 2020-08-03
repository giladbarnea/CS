#!/usr/bin/env python3
from pyperclip import paste, copy
import sys
from collections import OrderedDict
import re
from pathlib import Path
def get_pairs():
	old_new_pairs = [
		('<=>', '⇔'),
		('<->', '↔'),
		('or', '∨'),
		('|', '∨'),
		('v', '∨'),
		('and', 'Λ'),
		('&', 'Λ'),
		('0', '∅'),
		('->', '→'),
		('=>', '⇒'),
		('!<=', '⊈'),
		('<=', '⊆'),
		('!<', '⊄'),
		('<', '⊂'),
		('!in', '∉'),
		('in', '∈'),
		('u', '∪'),
		('union', '∪'),
		('n', '∩'),
		('intersection', '∩'),
		('not', '¬'),
		('-', '¬'),
		('~', '¬'),
		('All', '∀'),
		('Exists', '∃'),
		('equiv', '≡'),
		('equivalent', '≡'),
		('!=', '≠')
		]
	return old_new_pairs
def replacemany(text, old_new_pairs:list):
	replaced = text
	count = 0
	for old, new in old_new_pairs:
		count += replaced.count(old)
		replaced = replaced.replace(old,new)
	print(f'made {count} replacements')
	return replaced
def main(text:str):
	replaced = replacemany(text, get_pairs())
	copy(replaced)
	print(f'copied {len(replaced)} chars successfully')
	return replaced

if __name__ == "__main__":
	try:
		arg = sys.argv[1]

		if arg == '-h' or arg == '--help':
			newline = '\n'
			print(f"""
to_math.py
	clipboard in/out

to_math.py [path/to/file]
	modifies text in file, backs up to other file, copies result

to_math.py [TEXT]
	copies result and also prints it

to_math.py -h, --help
	this message

{newline.join(map(repr,get_pairs()))}
		""")
		elif Path(arg).is_file():
			with open(arg) as f:
				text = f.read()
			replaced = main(text)
			with open(f'{arg}.backup', mode='w') as f:
				f.write(text)
			with open(arg, mode='w') as f:
				f.write(replaced)

			print(f'replaced contents of {arg}, backed up to {arg}.backup')


		else:
			print('\n',main(arg))
	except IndexError:
		main(paste())