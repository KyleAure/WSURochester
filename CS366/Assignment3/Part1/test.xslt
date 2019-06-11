<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:template match="/">
        <xsl:element name="classes">
            <xsl:attribute name="count">
                <xsl:value-of select="count(//section[@delivery = 'Classroom'])" />
            </xsl:attribute>
            <xsl:apply-templates select="courses/course">
                <xsl:sort select="section/enrollment" data-type="number" />
            </xsl:apply-templates>
        </xsl:element>
    </xsl:template>

    <xsl:template match="course">
        <xsl:element name="class">
            <xsl:if test="section[@delivery = 'Classroom']">
            <xsl:attribute name="credits">
                <xsl:value-of select="@credits" />
            </xsl:attribute>
            <xsl:attribute name="room">
                <xsl:value-of select="section/room" />
            </xsl:attribute>
            <xsl:element name="number">
                <xsl:value-of select="@number" />
            </xsl:element>
            <xsl:element name="title">
                <xsl:value-of select="title" />
            </xsl:element>
            <xsl:element name="enrollment">
                <xsl:value-of select="section/enrollment" />
            </xsl:element>
            <xsl:apply-templates select="section[@delivery = 'Classroom']/instructor"/>
            </xsl:if>
        </xsl:element>
    </xsl:template>

    <xsl:template match="instructor">
        <xsl:copy-of select="." />
    </xsl:template>
</xsl:stylesheet>