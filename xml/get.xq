xquery version "1.0";
<HOLIDAYLIST>{
for $s in doc("holidays.xml")/HOLIDAYLIST/STATE[@value="NSW"]
return $s
}
</HOLIDAYLIST>