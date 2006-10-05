<?xml version="1.0"?>

<html xsl:version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
      xmlns="http://www.w3.org/1999/xhtml">
  
  <head>
    <title><xsl:text>jMock - </xsl:text><xsl:value-of select="head/title"/></title>
    <link media="screen" rel="stylesheet" title="Navigation View" type="text/css" href="{$base}/jmock.css"/>
    <link media="screen" rel="alternate stylesheet" type="text/css" href="{$base}/print.css" title="Print Preview"/>
    <link media="print" rel="stylesheet" type="text/css" href="{$base}/print.css"/>
    <xsl:copy-of select="head/*[not(name()='title')]"/>
  </head>
  
  <body>
    <div id="banner">
      <a href="/index.html"><img id="logo" src="/logo.gif" alt="jMock"/></a>
    </div>
	
    <div id="center" class="Content2Column">
      <div id="content">
        <xsl:copy-of select="html/body"/>
      </div>
      <div class="Meta">
         <p><a href="{$history}">Document History</a></p>
      </div>
    </div>
	
    <div class="SidePanel" id="left">
      <div class="MenuGroup">
        <h1><a href="/download.html">Software</a></h1>
        <ul>
          <li><a href="/download.html">Download</a></li>
          <li><a href="/repository.html">Anonymous CVS Access</a></li>
          <li><a href="/license.html">Project License</a></li>
          <li><a href="/CHANGELOG">Change Log</a></li>
        </ul>
      </div>

      <div class="MenuGroup">
        <h1><a href="/docs.html">Documentation</a></h1>
        <ul>
          <li><a href="/getting-started.html">Getting Started</a></li>
          <!-- <li><a href="">Frequently Asked Questions</a></li> -->
          <li><a href="/docs/javadoc/index.html" target="jmock-javadoc">JavaDocs</a></li>
          <li class="More"><a href="/docs.html">More...</a></li>
        </ul>
      </div>

      <div class="MenuGroup">
        <h1>User Support</h1>
        <ul>
          <li><a href="/mailing-lists.html">Mailing Lists</a></li>
          <li><a href="http://jira.codehaus.org/secure/BrowseProject.jspa?id=10336">Issue Tracker</a></li>
          <li><a href="/news-rss2.xml">News Feed (RSS 2.0)</a></li>
        </ul>
      </div>
    
      <div class="MenuGroup">
        <h1><a href="/development.html">Development</a></h1>
        <ul>
          <li><a href="/how-to-contribute.html">How to Contribute</a></li>
          <li><a href="/team.html">Development Team</a></li>
          <li class="More"><a href="/development.html">More...</a></li>
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
    
    <div class="Debug">
    	<xsl:copy-of select="html"/>
    </div>
  </body>
</html>
