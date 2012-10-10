xquery version "1.0";
for $s in doc('holidays.xml')/HOLIDAYLIST/STATE[@value='NSW']/HOLIDAY

where  $s/YEAR/MONTH='January' and $s/YEAR/DAY='1' and $s/YEAR/@value='2012'
return $s/TITLE
