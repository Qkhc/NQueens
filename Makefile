#------------------------------------------------------------------------------
# Makefile for NQueens problem
#------------------------------------------------------------------------------

JAVASRC    = NQueens.java Queen.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = NQueens
CLASSES    = NQueens.class Queen.class
JARFILE    = NQueens.jar
XTRA       = solution.txt

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE) $(XTRA)
