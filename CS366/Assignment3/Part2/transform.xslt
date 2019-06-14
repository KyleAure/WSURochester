<?xml version="1.0" encoding="UTF-8"?>
<!--
    Name: transform.xslt 
    Description: XML Transformation document for classes.xml
    Author: Kyle Jon Aure
    Date: 6/10/19
    Goal: classes -> graph
    Command: $ xsltproc transform.xslt courses.xml > output.svg
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <!-- Output Definition -->
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

	<!-- DOCUMENT ROOT Root Element: svg-->
	<xsl:template match="/">
		<xsl:comment>
		Name: output.svg
        Description: Generated output from courses.xml transformation
        Author: Kyle Jon Aure
        Date: 6/10/19
		</xsl:comment>

		<!-- SVG Element
		Viewport(1000px,1000px)
		Graph Area x-dist(1000px) y-dist(850)-->
		<svg xmlns="http://www.w3.org/2000/svg" 
		xmlns:xlink="http://www.w3.org/1999/xlink" 
		width="500" 
		height="500">
			<!-- Title - static -->
			<a xlink:href="courses.xml" >
			<text x="0" y="25" fill="black">Fall 2018 Enrollment</text>
			</a>

			<!-- Axis - static -->
			<line x1="0" y1="50" x2="0" y2="450" style="stroke:rgb(255,0,0);stroke-width:10"/>
			<line x1="0" y1="450" x2="500" y2="450" style="stroke:rgb(255,0,0);stroke-width:10"/>

			<!-- Variable: max enrollment -->
			<xsl:variable name="maxEnrollment">
				<xsl:for-each select="courses/course/section/enrollment">
			    	<xsl:sort select="." data-type="number" order="descending"/>
			    	<xsl:if test="position() = 1">
			    		<xsl:value-of select="."/>
			    	</xsl:if>
				</xsl:for-each>
			</xsl:variable>
			
			<!--Section Titles -->
			<xsl:for-each select="courses/course/section">
				<xsl:element name="text">
					<xsl:attribute name="x">
						<xsl:value-of select="((position() - 1) * (450 div count(//section)))+25"/>
					</xsl:attribute>
					<xsl:attribute name="y">475</xsl:attribute>
					<xsl:attribute name="fill">black</xsl:attribute>
					<xsl:value-of select="concat(../@number, '-', @number)"/>
				</xsl:element>
			</xsl:for-each>

			<!-- Bar Graphs -->
  			<xsl:for-each select="courses/course/section">
  				<xsl:element name="rect">
  					<xsl:attribute name="x">
  						<xsl:value-of select="((position() - 1) * (450 div count(//section)))+25"/>
  					</xsl:attribute>
  					<xsl:attribute name="y">
  						<xsl:value-of select="450 - (400 * (enrollment div $maxEnrollment))"/>
  					</xsl:attribute>
  					<xsl:attribute name="width">
  						<xsl:value-of select="(450 div count(//section))-25"/>
  					</xsl:attribute>
  					<xsl:attribute name="height">
  						<xsl:value-of select="400 * (enrollment div $maxEnrollment)" />
  					</xsl:attribute>
  					<xsl:attribute name="style">fill:blue;stroke:black;stroke-width:2</xsl:attribute>
  				</xsl:element>
  			</xsl:for-each>
		</svg>
	</xsl:template>
</xsl:stylesheet>