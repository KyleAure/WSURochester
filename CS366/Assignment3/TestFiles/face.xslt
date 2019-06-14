<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
xmlns="http://www.w3.org/2000/svg">

  <!-- main template -->
  <xsl:template match="/">
    <!-- root element is svg and its namespace -->
    <svg>
     <!-- <xsl:attribute name="xmlns">http://www.w3.org/2000/svg</xsl:attribute>-->

      <!-- move the whole picture for (20, 10) -->
      <g transform="translate(20,10)">

        <!-- caption of picture -->
        <text x="0" y="10" font-size="20">Brown Eyed Girl</text>
        <!-- apply templates -->
        <xsl:apply-templates select="face/eyes/eye"/>
        <xsl:apply-templates select="face/nose"/>
        <xsl:apply-templates select="face/mouth"/>
      </g>
    </svg>
  </xsl:template>

  
  <!-- eye template -->
  <xsl:template match="eye">

    <!-- eye is a rectangle starting at (35, 50), (65, 50), (95, 50), etc.

           w/ width 5*value and height 3*value -->
    <rect x="{position()*30 + 5}" y="50" width="{5*.}" height="{3*.}" fill="brown" stroke="black" stroke-width="2pt"/>
  </xsl:template>

  
  <!-- nose template -->
  <xsl:template match="nose">

    <!-- nose is a circle centered at (55, 80) w/ radius 4*value -->
    <circle cx="55" cy="80" r="{4*.}" stroke="black" stroke-width="1" fill="red"/>
  </xsl:template>

  
  <!-- mouth template -->
  <xsl:template match="mouth">

  <!-- mouth is a line centered at (55, 100) extended 5*value to two ends -->

  <!-- FF00FF is purple -->
    <line x1="{55-5*.}" y1="100" x2="{55+5*.}" y2="100" stroke="#FF00FF" stroke-width="4"/>
  </xsl:template>

  
</xsl:stylesheet>
