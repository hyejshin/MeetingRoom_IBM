/* ����Ʈ,ȸ�ǽ�����,��¥ �μ��� ��� �޾ƿͼ� Ajax �μ��� �Ѱ��ִ� �Լ� */
function allValues()
{
	var site = $('#site').val();
	var date = $('#date').val();
	var confer_nm = $('#confer_nm').val();
	
	callAjaxListByValues(site,date,confer_nm);
}

/* ȸ�ǽ� Tab�� Ŭ���� ������ ������ ���ϸ鼭 ��Ŀ�̵ǰ� ȸ�ǽ��� ���ڷ� �����ϴ� Ajax ȣ�� */
function changeConf(idx, name){
		var id = "";
		for(var i=1; i<=4; i++){
			id = "#conf" + i;
			$(id).removeClass('active');
		}
		id = "#conf" + String(idx);
		$(id).addClass('active');
		document.myForm.confer_nm.value = name;
		//allValues();
		
			
}

/* �޷°��� �ٲ𶧸��� ��Ŀ�̵ǰ� �޷°��� ���ڷ� �����ϴ� Ajax ȣ�� */


/* ȸ�ǽ� �̸�,��¥�� Ajax �μ��� �ѱ� */
function callAjaxListByValues(site,date,confer_nm)
{
	
	
	$.ajax({
        type: "post",
        url : "SelectByCondition.do",
        dataType : 'json',
        data: {
        	id1 : site,
        	id2 : date,
        	id3 : confer_nm
        },
       
        success : function(data) {
        	
        },
       
        error : function() {
        	console.log("error");
        }
	});
}





