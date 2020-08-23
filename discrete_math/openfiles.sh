#!/usr/bin/env zsh
cd /home/gilad/Documents/CompSci/discrete_math
PDF='mamans/past/maman_12__2019c.pdf'
VID='2020-08-05_lesson7_order_relations.mp4'
if [ -z "$(pgrep -fa textbook.pdf)" ]; then
  xdg-open textbook.pdf
else
  echo "textbook.pdf already open"
fi
if [ -z "$(pgrep -fa "$(basename $PDF)")" ]; then
  xdg-open $PDF
else
  echo "$PDF already open"
fi
source /home/gilad/Code/MyTool/mytool/scripts/util.sh

if [ -z "$(pgrep -fa $VID)" ]; then
  launch vlc lessons/2020-08-05_lesson7_order_relations/$VID
else
  echo "$VID already open"
fi
if [ -z "$(pgrep -fa "python3.* -m http.server")" ]; then
  launch python3 -m http.server
else
  echo "python3 -m http.server already open"
fi
xdg-open "http://localhost:8000/relations/relations.html"
