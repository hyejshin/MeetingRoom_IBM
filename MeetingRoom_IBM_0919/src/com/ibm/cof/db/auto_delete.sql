create event if not exists auto_delete
	on schedule
		every 10 second
		starts current_timestamp
    do
		delete from ibm.tb_reservation where rsv_reg_date < date_sub(date_format(now(),'%Y-%m-%d'),interval -10 month)


		/* Event install */
set global event_scheduler = on;
set @@global.event_scheduler = on;
set global event_scheduler = 1;
set @@global.event_scheduler = 1;
