CREATE TABLE tb_member (
  mem_seq int(11) NOT NULL AUTO_INCREMENT,
  mem_nm varchar(45) NOT NULL,
  mem_pn varchar(45) NOT NULL,
  mem_em varchar(45) NOT NULL,
  mem_site varchar(45) NOT NULL,
  mem_reg_date date NOT NULL,
  PRIMARY KEY (mem_seq,mem_em),
  UNIQUE KEY mem_pn_UNIQUE (mem_pn)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_reservation (
  rsv_seq int(11) NOT NULL AUTO_INCREMENT,
  rsv_site varchar(45) NOT NULL,
  rsv_confer_nm varchar(45) NOT NULL,
  rsv_date varchar(45) NOT NULL,
  rsv_start_time varchar(45) NOT NULL,
  rsv_end_time varchar(45) NOT NULL,
  rsv_title varchar(45) NOT NULL,
  rsv_mem_nm varchar(45) NOT NULL,
  rsv_mem_pn varchar(45) NOT NULL,
  rsv_mem_em varchar(45) NOT NULL,
  rsv_del_pw varchar(45) NOT NULL,
  rsv_reg_date date NOT NULL,
  rsv_repeat_seq int(11) DEFAULT NULL,
  PRIMARY KEY (rsv_seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_history (
  hst_seq int(11) NOT NULL AUTO_INCREMENT,
  hst_rsv_site varchar(45) NOT NULL,
  hst_rsv_confer_nm varchar(45) NOT NULL,
  hst_rsv_date varchar(45) NOT NULL,
  hst_rsv_start_time varchar(45) NOT NULL,
  hst_rsv_end_time varchar(45) NOT NULL,
  hst_rsv_title varchar(45) NOT NULL,
  hst_rsv_mem_nm varchar(45) NOT NULL,
  hst_rsv_mem_pn varchar(45) NOT NULL,
  hst_rsv_mem_em varchar(45) NOT NULL,
  hst_rsv_del_pw varchar(45) NOT NULL,
  hst_reg_date date NOT NULL,
  hst_state varchar(45) NOT NULL,
  PRIMARY KEY (hst_seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_conference (
  confrn_seq int(11) NOT NULL AUTO_INCREMENT,
  confrn_nm varchar(20) NOT NULL,
  confrn_site varchar(30) NOT NULL,
  confrn_order int(11) DEFAULT NULL,
  PRIMARY KEY (confrn_seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_admin (
  admin_seq int(11) NOT NULL AUTO_INCREMENT,
  admin_proj varchar(45) NOT NULL,
  admin_id varchar(45) NOT NULL,
  admin_pw varchar(45) NOT NULL,
  admin_month int(11) DEFAULT 2,
   PRIMARY KEY (admin_seq),
   UNIQUE KEY (admin_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;