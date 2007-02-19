<?xml version="1.0"?>

<xsl:stylesheet 
    version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:html="http://www.w3.org/1999/xhtml">
  
  <xsl:param name="base"/> <!-- The base URL of the site -->
  <xsl:param name="path"/> <!-- the path of the file below the base -->
  <xsl:param name="news"/> <!-- absolute URL of the news feed file in the workspace -->
  
  <xsl:output
      method="xml"
      version="1.0"
      encoding="utf8"
      doctype-public="-//W3C//DTD XHTML 1.1//EN"
      doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"
      indent="yes"/>
  
  
  <xsl:template match="html:html">
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
	<title>jMock - <xsl:value-of select="/html:html/html:head/html:title"/></title>
	<link media="screen" rel="stylesheet" title="Navigation View" type="text/css" href="jmock.css"/>
	<link media="screen" rel="alternate stylesheet" type="text/css" href="print.css" title="Print Preview"/>
	<link media="print" rel="stylesheet" type="text/css" href="print.css"/>
	<xsl:copy-of select="html:html/html:head/*[not(name()='title')]"/>
      </head>
      
      <body>
	<div id="banner">
	  <a href="{$base}/index.html"><img id="logo" src="logo.png" alt="jMock"/></a>
	</div>
	
	<div id="center">
	  <xsl:choose>
	    <xsl:when test="$path = 'index.html'">
	      <xsl:attribute name="class">Content3Column</xsl:attribute>
	    </xsl:when>
	    <xsl:otherwise>
	      <xsl:attribute name="class">Content2Column</xsl:attribute>
	    </xsl:otherwise>
	  </xsl:choose>
	  
	  <div id="content">
	    <xsl:apply-templates select="/html:html/html:body/*"/>
	  </div>
	  
          <xsl:if test="//html:a">
    	    <div class="LinkFootnotes">
	      <p class="LinkFootnotesHeader">Links:</p>
	      <xsl:for-each select="//html:a">
	        <p><xsl:number level="any" format="1"/>. <xsl:value-of select="."/>: <xsl:value-of select="@href"/></p>
	      </xsl:for-each>
	    </div>
          </xsl:if>
	  
	  <div class="Meta">
	    <p><a href="http://cvs.jmock.codehaus.org/jmock/website/content/{$path}">Document History</a></p>
	  </div>
	</div>
	
	<div class="SidePanel" id="left">
	  <div class="MenuGroup">
	    <h1><a href="download.html">Software</a></h1>
	    <ul>
	      <xsl:apply-templates select="document('../data/versions-jmock1.xml')"/>
	      <xsl:apply-templates select="document('../data/versions-jmock2.xml')"/>
	      <li><a href="repository.html">Anonymous CVS Access</a></li>
	      <li><a href="license.html">Project License</a></li>
	      <p class="NewsMore"><a href="versioning.html">About jMock version numbers...</a></p>
	    </ul>
	  </div>
	  
	  <div class="MenuGroup">
	    <h1><a href="docs.html">Documentation</a></h1>
	    <ul>
	      <li><a href="getting-started.html">Getting Started</a></li>
	      <li><a href="docs/javadoc/index.html" target="jmock-javadoc">JavaDocs</a></li>
	      <li class="More"><a href="docs.html">More...</a></li>
	    </ul>
	  </div>
	  
	  <div class="MenuGroup">
	    <h1>User Support</h1>
	    <ul>
	      <li><a href="mailing-lists.html">Mailing Lists</a></li>
	      <li><a href="http://jira.codehaus.org/secure/BrowseProject.jspa?id=10336">Issue Tracker</a></li>
	      <li><a href="news-rss2.xml">News Feed (RSS 2.0)</a></li>
	    </ul>
	  </div>
	  
	  <div class="MenuGroup">
	    <h1><a href="development.html">Development</a></h1>
	    <ul>
	      <li><a href="how-to-contribute.html">How to Contribute</a></li>
	      <li><a href="team.html">Development Team</a></li>
	      <li class="More"><a href="development.html">More...</a></li>
	    </ul>
	  </div>
	  
	  <div class="MenuGroup">
	    <h1>Extensions</h1>
	    <ul>
	      <li><a href="http://jdummy.sf.net">jDummy</a></li>
	    </ul>
	  </div>
	  
	  <div class="MenuGroup">
	    <h1>Related Sites</h1>
	    <ul>
	      <li><a href="http://www.mockobjects.com">All about Mock Objects</a></li>
	      <li><a href="http://www.junit.org">JUnit</a></li>
	      <li><a href="http://www.codehaus.org">Project hosted by Codehaus</a></li>
	    </ul>
	  </div>
	</div>
	
	<xsl:if test="$path = 'index.html'">
	  <div class="SidePanel" id="right">
	    <div class="NewsGroup">
	      <h1>Recent News</h1>
	      <xsl:for-each select="document('../content/news-rss2.xml')/rss/channel/item[position() &lt;= 5]">
		<div class="NewsItem">
		  <p class="NewsTitle"><xsl:value-of select="title"/></p>
		  <p class="NewsDate"><xsl:value-of select="pubDate"/></p>
		  <p class="NewsText"><xsl:copy-of select="html:div/node()"/></p>
		</div>
	      </xsl:for-each>	    
	      <p class="NewsMore"><a href="news-rss2.xml">News feed (RSS 2.0)</a></p>
	    </div>
	  </div>
	</xsl:if>
      </body>
    </html>
  </xsl:template>
  
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  
  <xsl:template match="html:a">
    <xsl:copy-of select="."/><sup class="LinkFootnoteRef"><xsl:number level="any" format="1"/></sup>
  </xsl:template>
  
  <xsl:template match="versions">
    <h2><xsl:value-of select="branch"/></h2>
    <ul>
      <xsl:for-each select="version">
	<li><a href="download.html"><xsl:value-of select="."/>: <xsl:value-of select="@number"/></a></li>
      </xsl:for-each>
    </ul>
  </xsl:template>
  
</xsl:stylesheet>
