<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="state" />

	<xsl:template match="HOLIDAYLIST">

		<xsl:apply-templates select="STATE[@value=$state]/HOLIDAYS"></xsl:apply-templates>

	</xsl:template>

	<xsl:template match="HOLIDAYS">
		<HOLIDAY>
			<xsl:value-of select="TITLE" />
		</HOLIDAY>
	</xsl:template>
</xsl:stylesheet>