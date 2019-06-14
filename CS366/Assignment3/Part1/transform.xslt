<?xml version="1.0" encoding="UTF-8"?>
<!--
    Name: transform.xslt
    Description: XML Transformation document for courses.xml
    Author: Kyle Jon Aure
    Date: 6/10/19
    Goal: Section -> Class
    Command: $ xsltproc transform.xslt courses.xml > output.xml
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <!-- Output Definition -->
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

    <!-- DOCUMENT ROOT Root Element: classes -->
    <xsl:template match="/">
        <xsl:comment>
        Name: output.xml
        Description: Generated output from courses.xml transformation
        Author: Kyle Jon Aure
        Date: 6/10/19
        </xsl:comment>
        <xsl:element name="classes">
            <xsl:attribute name="count">
                <xsl:value-of select="count(//section[@delivery='Classroom'])"/>
            </xsl:attribute>
            <xsl:apply-templates select="/courses/course/section">
                <xsl:sort select="enrollment" data-type="number" order="ascending"/>
            </xsl:apply-templates>
        </xsl:element>
    </xsl:template>

    <!-- Class Template 
    Parent: classes
    Example: <course number="341" credits="4.0">
    -->
    <xsl:template name="class" match="/courses/course/section">
        <xsl:if test="@delivery='Classroom'">
            <!-- Element: class-->
            <xsl:element name="class">
                <xsl:attribute name="credits">
                    <xsl:value-of select="../@credits"/>
                </xsl:attribute>
                <xsl:attribute name="room">
                    <xsl:value-of select="room"/>
                </xsl:attribute>
                <!-- Element: number-->
                <xsl:element name="number">
                    <xsl:value-of select="../@number"/>
                </xsl:element>
                <!-- Element: title-->
                <xsl:element name="title">
                    <xsl:value-of select="../title"/>
                </xsl:element>
                <!-- Element: enrollment-->
                <xsl:element name="enrollment">
                    <xsl:value-of select="enrollment"/>
                </xsl:element>
                <!-- Get instructor elements-->
                <xsl:apply-templates select="instructor"/>
                </xsl:element>
        </xsl:if>
    </xsl:template>
    

    <!-- Instructor Template
    Parent: class
    Example: <instructor><first>Nicole</first><last>Anderson</last></instructor>-->
    <xsl:template name="instructor" match="instructor">
        <!-- Element: instructor -->
        <xsl:copy-of select="."/>
    </xsl:template>
</xsl:stylesheet>