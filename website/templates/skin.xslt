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
      encoding="utf-8"
      doctype-public="-//W3C//DTD XHTML 1.1//EN"
      doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"
      indent="yes"/>
  
  <xsl:template match="html:html">
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
	<title>jMock - <xsl:value-of select="/html:html/html:head/html:title"/></title>
	<link media="screen" rel="stylesheet" type="text/css" href="jmock.css"/>
	<link media="print" rel="stylesheet" type="text/css" href="print.css"/>
	
	<meta http-equiv="Content-Script-Type" content="text/javascript"/>
	<script type="text/javascript" src="prefs.js"><xsl:comment/></script>
	
	<xsl:copy-of select="html:html/html:head/*[not(name()='title')]"/>
      </head>
      
      <body onload="restorePreferredTestFramework()">
	<div id="banner">
	  <a href="index.html"><img id="logo" src="logo.png" alt="jMock"/></a>
	  <div id="testFrameworkSelector">
	    Show examples for:
	    <a id="selectorJUnit3" href="#"
	       onclick="selectTestFramework('JUnit3')">JUnit 3</a>,
            <a id="selectorJUnit4" href="#"
	       onclick="selectTestFramework('JUnit4')">JUnit 4</a>,
            <a id="selectorRaw" href="#"
	       onclick="selectTestFramework('Raw')">Other</a>
	  </div>
	</div>
	
	<div id="center">
	  <xsl:choose>
	    <xsl:when test="$path = 'index.html'">
	      <xsl:attribute name="class">ContentWithNews</xsl:attribute>
	    </xsl:when>
	    <xsl:otherwise>
	      <xsl:attribute name="class">Content</xsl:attribute>
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
	</div>
	
	<div id="navigation">
	  <div class="MenuGroup">
	    <h1><a href="download.html">Software</a></h1>
	    <xsl:apply-templates select="document('../data/versions-jmock2.xml')"/>
	    <xsl:apply-templates select="document('../data/versions-jmock1.xml')"/>
	    <p><a href="repository.html">Anonymous CVS Access</a></p>
	    <p><a href="license.html">Project License</a></p>
	    <p><a href="versioning.html">Version Numbering</a></p>
	  </div>
	  
	  <div class="MenuGroup">
	    <h1>Documentation</h1>
	    <p><a href="getting-started.html">Getting Started</a></p>
	    <p><a href="cookbook.html">Cookbook</a></p>
	    <p><a href="cheat-sheet.html">Cheat Sheet</a></p>
	    <p><a href="javadoc/2.0.0/index.html">JavaDocs</a></p>
	    <p><a href="articles.html">Articles and Papers</a></p>
	    <p><a href="jmock1.html">jMock 1 Documentation</a></p>
	    <p><a href="http://www.mockobjects.com">mockobjects.com</a></p>
	  </div>
	  
	  <div class="MenuGroup">
	    <h1>User Support</h1>
	    <p><a href="mailing-lists.html">Mailing Lists</a></p>
	    <p><a href="http://jira.codehaus.org/secure/BrowseProject.jspa?id=10336">Issue Tracker</a></p>
	    <p><a href="news-rss2.xml">News Feed (RSS 2.0)</a></p>
	  </div>
	  
	  <div class="MenuGroup">
	    <h1>Credits</h1>
	    <p><a href="team.html">Development Team</a></p>
	    <p><a href="http://www.codehaus.org">Project hosted by Codehaus</a></p>
            <p><a href="http://www.tango-project.org">Icons by the Tango Project</a></p>
	  </div>
	</div>
	
	<xsl:if test="$path = 'index.html'">
	  <div id="news">
	    <div class="NewsGroup">
	      <h1>Recent News</h1>
	      <xsl:for-each select="document('../content/news-rss2.xml')/rss/channel/item[position() &lt;= 5]">
		<div class="NewsItem">
		  <h2 class="NewsTitle"><a><xsl:attribute name="href"><xsl:value-of select="link"/></xsl:attribute><xsl:value-of select="title"/></a></h2>
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
