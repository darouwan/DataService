<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<TITLE>Show</TITLE>
	<BODY>

		<xsl:param name="short" />
		<xsl:template match="HOLIDAYLIST">

			<table width="90%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td></td>
					<td>2012</td>
					<td>2013</td>
				</tr>
				<xsl:apply-templates select="STATE[@value=$short]/HOLIDAY"></xsl:apply-templates>

			</table>
		</xsl:template>


		<xsl:template match="HOLIDAY">
			<tr>
				<td>
					<xsl:value-of select="TITLE" />
				</td>

				<xsl:apply-templates select="YEAR"></xsl:apply-templates>

			</tr>
		</xsl:template>

		<xsl:template match="YEAR">
			<td>
				<xsl:value-of select="MONTH" />
				<br />
				<xsl:value-of select="DAY" />
				<br />
				<xsl:value-of select="WEEKDAY" />
			</td>
		</xsl:template>

	</BODY>
</xsl:stylesheet>
