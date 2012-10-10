<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:param name="short" />
	<xsl:template match="HOLIDAYLIST">
		<HOLIDAYLIST>
			<STATE>
				<xsl:attribute name="value">
<xsl:value-of select="$short" />
</xsl:attribute>
				<xsl:apply-templates select="STATE[@value=$short]/HOLIDAY"></xsl:apply-templates>
			</STATE>
		</HOLIDAYLIST>
	</xsl:template>

	<xsl:template match="holiday">
		<HOLIDAY>
			<TITLE>
				<xsl:value-of select="TITLE" />
			</TITLE>

			<xsl:apply-templates select="YEAR"></xsl:apply-templates>

		</HOLIDAY>
	</xsl:template>

	<xsl:template match="YEAR">
		<YEAR>
			<xsl:attribute name="value">
<xsl:value-of select="@value" />
</xsl:attribute>

			<MONTH>
				<xsl:value-of select="MONTH" />
			</MONTH>
			<DAY>
				<xsl:value-of select="DAY" />
			</DAY>
			<WEEKDAY>
				<xsl:value-of select="WEEKDAY" />
			</WEEKDAY>
		</YEAR>
	</xsl:template>

</xsl:stylesheet>
