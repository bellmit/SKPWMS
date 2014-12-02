
var aa = 1;
var bb = 1;
var ret;
var address,address1,address2,address3;

MWRFATL.CloseReader();
	
function RfInit()//打开设备
{
        MWRFATL.CloseReader();
        var pass="明泰";
	bb=MWRFATL.OpenReader(pass);
        aa=MWRFATL.LastRet;
        if(aa==0)				
	alert("硬件版本号为："+bb);
        else
        alert("打开设备Fail!");
}

function RfExit()//关闭设备
{
	MWRFATL.CloseReader();
        aa=MWRFATL.LastRet;
        if(aa==0)
        alert("关闭设备Sucess!");
        else
        alert("关闭设备Fail!");
}
	

function DevBeep()//蜂鸣器蜂鸣
{
	bb=MWRFATL.RF_Beep(10);		//传入参数：设备单次蜂鸣时间
	aa=MWRFATL.LastRet;
        if(aa==0)
        alert("蜂鸣Sucess!");
        else
        alert("蜂鸣Fail!");
}

function RfHex()
{
        var cdata2=cval.value;
        var len=document.getElementById('cval').value.length;
        bb=MWRFATL.MF_hex_a(cdata2 , len)
        aa=MWRFATL.LastRet;
        if(aa==0)				
	alert("hex转换数据为："+bb);
        else
        alert("hex转换Fail!");
}

function RfAsc()
{
        var cdata1=cval.value;
        var len=document.getElementById('cval').value.length;
        bb=MWRFATL.MF_a_hex(cdata1,len)
        aa=MWRFATL.LastRet;
        if(aa==0)				
	alert("asc转换数据为："+bb);
        else
        alert("asc转换Fail!");
}

//-------------------------------------------------------	M1卡数据操作	 ----------------------------------------------------------//


//说明： 装载密钥 	
//参数：mode: 密码类型
//            0 — KEY A
//            4 — KEY B
//      secnr: 须装载密码的扇区号(0～15) 
//      key:  写入读写器的12字节新密码	  
// 返回： <0 错误		  
//        =0 正确
function RfLoadkey()
{
        key = keyin.value;
        sec = secin.value;
        ret = document.getElementById('keyMode').value;
        //alert(ret);
	MWRFATL.RF_LoadKey(ret,sec,key);
	aa=MWRFATL.LastRet;
	if(aa==0)
        alert("装载Sucess!");
        else
        alert("装载Fail!");
}


// 说明： 寻卡
// 返回： <>0 错误	  
//        =0 正确
function RfCard()
{      
        MWRFATL.MF_Reset(5);
	bb=MWRFATL.OpenCard(1);
	aa=MWRFATL.LastRet;
	if(aa==0)
        alert("卡片序列号为："+bb );
        else
        alert("寻卡Fail!");
}

// 说明： 核对密码
// 返回： <>0 错误	  
//        =0 正确
function RfAuthentication()
{
        sec = secin.value;
	MWRFATL.RF_Authentication(ret,sec);
	aa=MWRFATL.LastRet;
	if(aa==0)
        alert("验证Sucess!");
        else
        alert("验证Fail!");
}

// 说明： 读数据 
// 返回： <>0 错误	  
//        =0 正确
function RfRead()
{
		var sec = secin.value;
        if(sec>15){
			alert("SecError!");
			ret;
		}
		
        address=sec*4;
        address1=address+1;
        address2=address+2;
        address3=address+3;

	var block=MWRFATL.MF_Read(address); 
        RData.value=(block.slice(0,32));    
        var block1=MWRFATL.MF_Read(address1);
        RData1.value=(block1.slice(0,32));
        var block2=MWRFATL.MF_Read(address2);
        RData2.value=(block2.slice(0,32));
        var block3=MWRFATL.MF_Read(address3);
        aa=MWRFATL.LastRet;
        if(aa==0)
       {
        RData3.value=(block3.slice(0,32));
       }
        else
       {
        RData.value="";
        RData1.value="";
        RData2.value="";
        RData3.value="";
        alert("Read_Fail!");
       }
}


// 说明： 写数据
// 返回： <>0 错误		  
//        =0 正确
function RfWrite()
{
		var sec = secin.value;
        if (sec>15) {
			alert("SecError!");
			ret;
		}
		
        address=sec*4;
        address1=address+1;
        address2=address+2;
        address3=address+3;
        
        //alert(RData.value);
	MWRFATL.MF_Write(address,RData.value);
	MWRFATL.MF_Write(address1,RData1.value);
	MWRFATL.MF_Write(address2,RData2.value);
	//MWRFATL.MF_Write(address3,RData3.value);
        aa=MWRFATL.LastRet;
	if(aa==0)
        alert("Write_Sucess!");      
        else
        alert("Write_Fail!"); 
     
}

// 说明： 修改密码 	  
// 返回： <>0 错误	  
//        =0 正确
function RfChangekey()
{
		var sec = secin.value;
        if(sec>15) {
			alert("SecError!");
			ret;
		}
        address=sec*4;
        address3=address+3;
        
        var Changekey = keyin.value + "FF078069FFFFFFFFFFFF";
        MWRFATL.MF_Write(address3,Changekey);
        aa=MWRFATL.LastRet;
	if(aa==0)
        alert("Changekey_Sucess!");      
        else
        alert("Changekey_Fail!"); 
        
}

// 说明：中止卡片
// 返回： <>0 错误	  
//        =0 正确
function RfHalt()
{
       MWRFATL.CloseCard();
       aa=MWRFATL.LastRet;
       if(aa==0)
       alert("中止Sucess!");
       else
       alert("中止Fail!");
}
