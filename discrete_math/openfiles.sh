#!/usr/bin/env zsh
BASEPATH='/home/gilad/Documents/CompSci/discrete_math'
cd $BASEPATH || return 1
xdg-open $BASEPATH
PDF='mamans/past/2019c/פתרון ממן 13.pdf'
VID='2020-08-10_lesson8_functions_powers/2020-08-10_lesson8_functions_powers.mp4'
if [ -z "$(pgrep -fa textbook.pdf)" ]; then
  xdg-open textbook.pdf
else
  echo "textbook.pdf already open"
fi
if [ -z "$(pgrep -fa "$(basename "$PDF")")" ]; then
  xdg-open "$PDF"
else
  echo "$PDF already open"
fi
source /home/gilad/Code/MyTool/mytool/scripts/util.sh

if [ -z "$(pgrep -fa $VID)" ]; then
  launch vlc lessons/$VID
else
  echo "$VID already open"
fi
if [ -z "$(pgrep -fa "python3.* -m http.server")" ]; then
  launch python3 -m http.server
else
  echo "python3 -m http.server already open"
fi
launch xdg-open "http://localhost:8000/relations/relations.html"
launch xdg-open "http://localhost:8000/functions_powers/functions_powers.html"

# search:
# grep -nIrEH "קבוצת כל המספרים הממשיים האי" "."
