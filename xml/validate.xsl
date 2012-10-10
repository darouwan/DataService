<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:param name="state" />
	<xsl:param name="month" />
	<xsl:param name="day" />
	<xsl:param name="year" />

	<xsl:template match="HOLIDAYLIST">
		<holiday>
			<xsl:apply-templates select="STATE[@value=$state]/HOLIDAY"></xsl:apply-templates>
		</holiday>
	</xsl:template>

	<xsl:template match="HOLIDAY">
		<xsl:variable name="xmonth" select="YEAR/MONTH" />
		<xsl:variable name="xday" select="YEAR/DAY" />

		<xsl:for-each select="YEAR">
			<xsl:variable name="xyear" select="@value"></xsl:variable>
			<xsl:if test="$xyear='2012'">
				<xsl:if test="$xmonth='January'">
					<xsl:if test="$xday='1'">
						<a>
						<xsl:value-of select="../TITLE"></xsl:value-of>
						</a>
					</xsl:if>

				</xsl:if>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>

</xsl:stylesheet>
