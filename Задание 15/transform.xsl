<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Mobile Tariffs</title>
                <style> table { border-collapse: collapse; width: 100%; } th, td { border: 1px solid black; padding: 8px; text-align: left; } th { background-color: #f2f2f2; } </style>
            </head>
            <body>
                <h1>Mobile Tariffs</h1>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Operator Name</th>
                        <th>Payroll (руб/мес)</th>
                        <th>In-Network Call Price (руб/мин)</th>
                        <th>Out-Network Call Price (руб/мин)</th>
                        <th>Landline Call Price (руб/мин)</th>
                        <th>SMS Price (руб)</th>
                        <th>Favorite Number</th>
                        <th>Billing</th>
                        <th>Connection Fee (руб)</th>
                    </tr>
                    <xsl:for-each select="//Plan">
                        <xsl:sort select="Payroll" data-type="number"/>
                        <tr>
                            <td>
                                <xsl:value-of select="Name"/>
                            </td>
                            <td>
                                <xsl:value-of select="OperatorName"/>
                            </td>
                            <td>
                                <xsl:value-of select="Payroll"/>
                            </td>
                            <td>
                                <xsl:value-of select="CallPrices/InNetwork"/>
                            </td>
                            <td>
                                <xsl:value-of select="CallPrices/OutNetwork"/>
                            </td>
                            <td>
                                <xsl:value-of select="CallPrices/Landline"/>
                            </td>
                            <td>
                                <xsl:value-of select="SMSPrice"/>
                            </td>
                            <td>
                                <xsl:value-of select="Parameters/FavoriteNumber"/>
                            </td>
                            <td>
                                <xsl:value-of select="Parameters/Billing"/>
                            </td>
                            <td>
                                <xsl:value-of select="Parameters/ConnectionFee"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>