# Make sure you have installed the catalog files for XHTML on your local filesystem

BASE=http://www.jmock.org

CONTENT=$(shell find content -type f -and -not -wholename '*/CVS/*')
OUTPUT=$(CONTENT:content/%=build/%)

all: $(OUTPUT)

build/%.html: content/%.html templates/skin.xslt
	@mkdir -p build
	xsltproc \
	  --nonet --nodtdattr \
	  --stringparam base $(BASE) \
	  --param isindex false \
	  --stringparam history http://cvs.jmock.codehaus.org/jmock/website/content/$*.html \
	  templates/skin.xslt $< | xmllint --format - > $@

build/%: content/%
	@mkdir -p build
	cp $< $@

clean:
	rm -rf build

again: clean all