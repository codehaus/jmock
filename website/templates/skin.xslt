<?xml version="1.0"?>

<xsl:stylesheet 
	version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:html="http://www.w3.org/1999/xhtml">
	
  <xsl:param name="base"/> <!-- The base URL of the site -->
  <xsl:param name="path"/> <!-- the path of the file below the base -->
  <xsl:param name="news"/> <!-- absolute URL of the news feed file in the workspace -->
  
  <xsl:template match="/">
	<html>
	  <head>
	    <title>jMock - <xsl:value-of select="/html:html/html:head/html:title"/></title>
	    <link media="screen" rel="stylesheet" title="Navigation View" type="text/css" href="{$base}/jmock.css"/>
	    <link media="screen" rel="alternate stylesheet" type="text/css" href="{$base}/print.css" title="Print Preview"/>
	    <link media="print" rel="stylesheet" type="text/css" href="{$base}/print.css"/>
	    <xsl:copy-of select="html:html/html:head/*[not(name()='title')]"/>
	  </head>
	  
	  <body>
	    <div id="banner">
	      <a href="{$base}/index.html"><img id="logo" src="{$base}/logo.gif" alt="jMock"/></a>
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
	        <xsl:copy-of select="/html:html/html:body/*"/>
	      </div>
	      <div class="Meta">
	         <p><a href="http://cvs.jmock.codehaus.org/jmock/website/content/{$path}">Document History</a></p>
	      </div>
	    </div>
	    
	    <div class="SidePanel" id="left">
	      <div class="MenuGroup">
	        <h1><a href="{$base}/download.html">Software</a></h1>
	        <ul>
	          <li><a href="{$base}/download.html">Download</a></li>
	          <li><a href="{$base}/repository.html">Anonymous CVS Access</a></li>
	          <li><a href="{$base}/license.html">Project License</a></li>
	        </ul>
	      </div>
	
	      <div class="MenuGroup">
	        <h1><a href="{$base}/docs.html">Documentation</a></h1>
	        <ul>
	          <li><a href="{$base}/getting-started.html">Getting Started</a></li>
	          <li><a href="{$base}/docs/javadoc/index.html" target="jmock-javadoc">JavaDocs</a></li>
	          <li class="More"><a href="/docs.html">More...</a></li>
	        </ul>
	      </div>
	
	      <div class="MenuGroup">
	        <h1>User Support</h1>
	        <ul>
	          <li><a href="{$base}/mailing-lists.html">Mailing Lists</a></li>
	          <li><a href="http://jira.codehaus.org/secure/BrowseProject.jspa?id=10336">Issue Tracker</a></li>
	          <li><a href="{$base}/news-rss2.xml">News Feed (RSS 2.0)</a></li>
	        </ul>
	      </div>
	    
	      <div class="MenuGroup">
	        <h1><a href="{$base}/development.html">Development</a></h1>
	        <ul>
	          <li><a href="{$base}/how-to-contribute.html">How to Contribute</a></li>
	          <li><a href="{$base}/team.html">Development Team</a></li>
	          <li class="More"><a href="{$base}/development.html">More...</a></li>
	        </ul>
	      </div>
	
	      <div class="MenuGroup">
	        <h1>Extensions</h1>
	        <ul>
	          <li><a href="http://jdummy.sf.net">jDummy</a></li>
	        </ul>
	      </div>
	      
	      <div class="MenuGroup">
	        <h1>Ports to Other Languages</h1>
	        <ul>
	          <li><a href="http://www.nmock.org">nMock: mock objects for .NET</a></li>
	          <li><a href="http://mockpp.sf.net">mockPP: mock objects for C++</a></li>
	          <li><a href="http://pmock.sf.net">PMock: mock objects for Python</a></li>
	        </ul>
	      </div>
	      
	      <div class="MenuGroup">
	        <h1>Related Sites</h1>
	        <ul>
	          <li><a href="http://www.mockobjects.com">Mock Objects</a></li>
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
	          <p class="NewsMore"><a href="{$base}/news-rss2.xml">News feed (RSS 2.0)</a></p>
	        </div>
	      </div>
	    </xsl:if>
	  </body>
	</html>
  </xsl:template>
</xsl:stylesheet>
