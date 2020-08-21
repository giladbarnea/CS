import markdown
import os, sys

mkin = open(sys.argv[1])
md = markdown.Markdown(extensions=[
    # 'toc(title=Table of Contents)',
    'codehilite',
    'meta',
    'tables'])
gen_html = md.convert(mkin.read())
md_meta = md.Meta

output = [f"""<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <link href="white.css" type="text/css" rel="stylesheet" />
    <link href="markdown.css" type="text/css" rel="stylesheet" />
  </head>

<body>
""", gen_html, """

</body>

</html>
"""]

outfile = open(sys.argv[2], 'w')
outfile.write(''.join(output))
outfile.close()

# end
