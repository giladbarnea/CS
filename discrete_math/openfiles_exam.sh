#!/usr/bin/env bash
function masterpdf(){
	/home/gilad/.local/share/master-pdf-editor-5/masterpdfeditor5 "${*}" &
	sleep 0.1s
}
# xdg-open "/home/gilad/Documents/CompSci/discrete_math/Exams/exams_index.odt"
masterpdf "/home/gilad/Documents/CompSci/20476_discrete_math/Exams/2019/2019a2_83/2019a2_m83_solved_grade97.pdf"
masterpdf "/home/gilad/Documents/CompSci/20476_discrete_math/Exams/2019/2019a2_83/2019a2_m83.pdf"
masterpdf "/home/gilad/Documents/CompSci/20476_discrete_math/Summary/2 summary.pdf"
masterpdf "/home/gilad/Documents/CompSci/20476_discrete_math/Summary/1 Summary.pdf"
masterpdf "/home/gilad/Documents/CompSci/20476_discrete_math/Summary/3 סיכום בדידה.pdf"
masterpdf "/home/gilad/Documents/CompSci/20476_discrete_math/Summary/מיכל וולף שיעורים/מפגש 5 2020א.pdf"
masterpdf "/home/gilad/Documents/CompSci/20476_discrete_math/Summary/קבוצות - סיכום עם תוספות.pdf"
masterpdf "/home/gilad/Documents/CompSci/discrete_math/תורת הקבוצות דיגיטלי_עם הערות.pdf"
# xdg-open "/home/gilad/Documents/CompSci/20476_discrete_math/Summary"
# Lotion
# if nautilus "/home/gilad/Documents/CompSci/20476_discrete_math/Exams/2020"; then

# 	nautilus "/home/gilad/Documents/CompSci/20476_discrete_math"
# 	nautilus "/home/gilad/Documents/CompSci/discrete_math"
# fi