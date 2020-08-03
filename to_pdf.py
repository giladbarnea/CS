#!/usr/bin/env python3
from pathlib import Path
import sys
from fpdf import FPDF

file = Path(sys.argv[1])

pdf = FPDF()

pdf.add_page()

pdf.add_font('DejaVuBold', '', '/usr/share/fonts/truetype/dejavu/DejaVuSerif-Bold.ttf', uni=True)
pdf.add_font('DejaVu', '', '/usr/share/fonts/truetype/dejavu/DejaVuSerif.ttf', uni=True)

pdf.set_font('DejaVu', size=10)

with open(file) as f:
    txt = f.read()

pdf.multi_cell(200, 5, txt=txt)
pdf.output(file.with_suffix('.pdf'))
