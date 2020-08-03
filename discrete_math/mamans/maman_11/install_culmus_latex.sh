#!/bin/bash
## This script adds Ivritex/Culmus Hebrew LaTeX support for a Leopard MacTex
## installaion.
##
## It assumes you have "wget" installed (wget downloads files from the web.  If
##  you don't have it installed, just download them yourself and put them in the
##  tmp_latex_install directory)
##

mkdir tmp_latex_install
cd tmp_latex_install
# download culmus (fonts) and ivritex (latex support)
wget http://switch.dl.sourceforge.net/sourceforge/culmus/culmus-0.100-iso8859-8.tar.gz
wget http://heanet.dl.sourceforge.net/sourceforge/ivritex/culmus-latex-0.6.tar.gz

# unzip
tar zxvf culmus-0.100-iso8859-8.tar.gz
tar zxvf culmus-latex-0.6.tar.gz

# install culmus fonts
mv ISO-8859-8 ~/Library/culmus-fonts/
#xset +fp $HOME/Library/culmus-fonts
#xset fp rehash

# install ivritex
#mkdir -p $HOME/Library/texmf/
cd culmus-latex-0.6
#make TEXMFDIR=$HOME/Library/texmf/ CULMUSDIR=$HOME/Library/culmus-fonts
#sudo make TEXMFDIR=$HOME/Library/texmf/ CULMUSDIR=$HOME/Library/culmus-fonts install

cd ../..
rm -rf tmp_latex_install
