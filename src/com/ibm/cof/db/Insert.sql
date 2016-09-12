insert into tb_conference (CONFRN_NM, CONFRN_SITE, CONFRN_STAT) 
               values ('코회의실2','코웨이','Y');
insert into tb_conference (CONFRN_NM, CONFRN_SITE, CONFRN_STAT) 
               values ('코회의실3','코웨이','Y');
insert into tb_conference (CONFRN_NM, CONFRN_SITE, CONFRN_STAT) 
               values ('코회의실4','코웨이','Y');
               
insert into tb_conference (CONFRN_NM, CONFRN_SITE, CONFRN_STAT) 
               values ('아회의실1','아모레퍼시픽','Y');
insert into tb_conference (CONFRN_NM, CONFRN_SITE, CONFRN_STAT) 
               values ('아회의실2','아모레퍼시픽','Y');
insert into tb_conference (CONFRN_NM, CONFRN_SITE, CONFRN_STAT) 
               values ('아회의실3','아모레퍼시픽','Y');
insert into tb_conference (CONFRN_NM, CONFRN_SITE, CONFRN_STAT) 
               values ('아회의실4','아모레퍼시픽','Y');
               


insert into tb_reservation (rsv_site, rsv_confer_nm, rsv_date, rsv_start_time, rsv_end_time,
rsv_title, rsv_mem_nm, rsv_mem_pn, rsv_mem_em, rsv_del_pw, rsv_reg_date) 
values ('아모레퍼시픽', '회의실1', '2016-09-12', '1400', '1600', 'important meeting',
'신혜정', '01072712808', 'shinsy11@naver.com', '1234', '2016-09-12');