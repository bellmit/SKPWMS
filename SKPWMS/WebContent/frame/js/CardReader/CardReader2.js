
var aa = 1;
var bb = 1;
var ret;
var address,address1,address2,address3;

//
var keyin="FFFFFFFFFFFF";
var secin;
var keyMode;



//MWRFATL.CloseReader();
	
function RfInit()//打开设备
{
        MWRFATL.CloseReader();
        var pass="明泰";
	bb=MWRFATL.OpenReader(pass);
        aa=MWRFATL.LastRet;
        //if(aa==0)				
	//console.info("硬件版本号为："+bb);
        //else
        //console.info("打开设备Fail!");
}

function RfExit()//关闭设备
{
	MWRFATL.CloseReader();
        aa=MWRFATL.LastRet;
        //if(aa==0)
        //console.info("关闭设备Sucess!");
        //else
        //console.info("关闭设备Fail!");
}
	

function DevBeep()//蜂鸣器蜂鸣
{
	bb=MWRFATL.RF_Beep(10);		//传入参数：设备单次蜂鸣时间
	aa=MWRFATL.LastRet;
        //if(aa==0)
        //console.info("蜂鸣Sucess!");
        //else
        //console.info("蜂鸣Fail!");
}

function RfHex()
{
        var cdata2=cval.value;
        var len=document.getElementById('cval').value.length;
        bb=MWRFATL.MF_hex_a(cdata2 , len)
        aa=MWRFATL.LastRet;
       // if(aa==0)				
	//console.info("hex转换数据为："+bb);
       // else
        //console.info("hex转换Fail!");
}

function RfAsc()
{
        var cdata1=cval.value;
        var len=document.getElementById('cval').value.length;
        bb=MWRFATL.MF_a_hex(cdata1,len)
        aa=MWRFATL.LastRet;
        //if(aa==0)				
	//console.info("asc转换数据为："+bb);
        //else
        //console.info("asc转换Fail!");
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
        key = keyin;
        sec = secin;
        ret = keyMode;
        //console.info(ret);
	MWRFATL.RF_LoadKey(ret,sec,key);
	aa=MWRFATL.LastRet;
	//if(aa==0)
        //console.info("装载Sucess!");
        //else
        //console.info("装载Fail!");
}


// 说明： 寻卡
// 返回： <>0 错误	  
//        =0 正确
function RfCard()
{      
        MWRFATL.MF_Reset(5);
	bb=MWRFATL.OpenCard(1);
	aa=MWRFATL.LastRet;
	if(aa==0){
		//console.info("卡片序列号为："+bb );
		return bb;
	}
      //  else
        //console.info("寻卡Fail!");
}

// 说明： 核对密码
// 返回： <>0 错误	  
//        =0 正确
function RfAuthentication()
{
        sec = secin;
	MWRFATL.RF_Authentication(ret,sec);
	aa=MWRFATL.LastRet;
	//if(aa==0)
        //console.info("验证Sucess!");
       // else
       // console.info("验证Fail!");
}

// 说明： 读数据 
// 返回： <>0 错误	  
//        =0 正确
function RfRead()
{
		var sec = secin;
        if(sec>15){
			//console.info("SecError!");
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
        //console.info("Read_Fail!");
       }
}


// 说明： 写数据
// 返回： <>0 错误		  
//        =0 正确
function RfWrite()
{
		var sec = secin;
        if (sec>15) {
			//console.info("SecError!");
			ret;
		}
		
        address=sec*4;
        address1=address+1;
        address2=address+2;
        address3=address+3;
        
        //console.info(RData.value);
	MWRFATL.MF_Write(address,RData.value);
	MWRFATL.MF_Write(address1,RData1.value);
	MWRFATL.MF_Write(address2,RData2.value);
	//MWRFATL.MF_Write(address3,RData3.value);
        aa=MWRFATL.LastRet;
	//if(aa==0)
        //console.info("Write_Sucess!");      
       // else
        //console.info("Write_Fail!"); 
     
}

// 说明： 修改密码 	  
// 返回： <>0 错误	  
//        =0 正确
function RfChangekey()
{
		var sec = secin;
        if(sec>15) {
			//console.info("SecError!");
			ret;
		}
        address=sec*4;
        address3=address+3;
        
        var Changekey = keyin.value + "FF078069FFFFFFFFFFFF";
        MWRFATL.MF_Write(address3,Changekey);
        aa=MWRFATL.LastRet;
	//if(aa==0)
        //console.info("Changekey_Sucess!");      
       // else
        //console.info("Changekey_Fail!"); 
        
}

// 说明：中止卡片
// 返回： <>0 错误	  
//        =0 正确
function RfHalt()
{
       MWRFATL.CloseCard();
       aa=MWRFATL.LastRet;
      // if(aa==0)
      // console.info("中止Sucess!");
     //  else
      // console.info("中止Fail!");
}


function WriteFactoryID(ID) 
{
    var sec = 4;
    var dataLen = 8;
    var dataStart = 0;
    var dataEnd = 8;
    if (typeof ID!="number") {
//        alert("参数错误");
        return false;
    }
    ID %= 0xFFFFFFFF;
    var hex = parseInt(ID, 10).toString(16);
    var data = MWRFATL.MF_Read(sec);
    var ret = MWRFATL.LastRet;
    if (ret == 0) {
        ;
    } else {

    }
    data = data.slice(0, 32);
    data = padLeft(hex, dataLen) + data.substring(dataEnd);
    MWRFATL.MF_Write(sec, data);
    ret = MWRFATL.LastRet;
    if (ret == 0) {
//        alert("写入ID成功");
    	return true;
    } else {
//        alert("写入ID失败");
        return false;
    }
}

function WriteControlerID(ID)
{
    var sec = 4;
    var dataLen = 8;
    var dataStart = 8;
    var dataEnd = 16;
    if (typeof ID != "number") {
//        alert("参数错误");
    	return false;
    }
    ID %= 0xFFFFFFFF;
    var hex = parseInt(ID, 10).toString(16);
    var data = MWRFATL.MF_Read(sec);
    var ret = MWRFATL.LastRet;
    if (ret == 0) {
        ;
    } else {
        ;
    }

    data = data.slice(0, 32);
    data = data.substring(0, dataStart) + padLeft(hex, dataLen) + data.substring(dataEnd);
    MWRFATL.MF_Write(sec, data);
    ret = MWRFATL.LastRet;
    if (ret == 0) {
//        alert("写入控制器ID成功");
        return true;
    } else {
//        alert("写入控制器ID失败");
    	return false;
    }
}

function WriteSerialNo(No)
{
    var sec = 4;
    var dataLen = 8;
    var dataStart = 16;
    var dataEnd = 24;
    if (typeof No != "number") {
//        alert("参数错误");
    	return false;
    }
    No %= 0xFFFFFFFF;
    var hex = parseInt(No, 10).toString(16);

    var data = MWRFATL.MF_Read(sec);
    var ret = MWRFATL.LastRet;
    if (ret == 0) {
        ;
    } else {
        ;
    }
    data = data.slice(0, 32);

    data = data.substring(0, dataStart) + padLeft(hex, dataLen) + data.substring(dataEnd);
    MWRFATL.MF_Write(sec, data);
    ret = MWRFATL.LastRet;
    if (ret == 0) {
//        alert("写入充值流水号成功");
    	return true;
    } else {
//        alert("写入充值流水号失败");
    	return false;
    }
}

function WriteOrgCode(code) 
{
    var codeLen = 9;
    var sec = 5;
    if (typeof(code) != "string") {
//        alert("参数错误");
        return false;
    }
    code = code.slice(0, codeLen);
    var hex = "";
    for (var i = 0; i < codeLen; i++) {
        hex += code.charCodeAt(i).toString(16);
    }

    for (var i = 0; i < 32-codeLen*2; i++) {
        hex += "0";
    }

    MWRFATL.MF_Write(sec, hex);
    var ret = MWRFATL.LastRet;
    if (0 == ret) {
        return true;
    } else {
//        alert("写组织机构代码失败");
    	return false;
    }
}

function padLeft(str, lenght) 
{
    if (str.length >= lenght)
        return str;
    else
        return padLeft("0" +str, lenght);
}

function padRight(str, lenght) 
{
    if(str.length >= lenght)
        return str;
    else
        return padRight(str+"0",lenght);
}

//读取物理卡号
function readCardNo(){
	RfInit();//打开设备
	var cardNo=RfCard();//寻卡
	RfExit();//关闭设备
	//DevBeep();//蜂鸣器蜂鸣
	return cardNo;
}

//写充值流水号
function writeRechargeNo(rNo){
	var result=false;
	
	keyMode="0";
	secin="1";
	
	RfInit();//打开设备
	var cardNo=RfCard();//寻卡
	if(cardNo){
		RfLoadkey();//装载密钥 
		RfAuthentication();//核对密码
//		MWRFATL.MF_Write(4,rNo);
//		WriteSerialNo(rNo.parseInt());
//		aa=MWRFATL.LastRet;
//		if(aa==0){
		if(WriteSerialNo(parseInt(rNo))){
			//console.info("Write_Sucess!");  
			result=true;
		}
		//else
			//console.info("Write_Fail!"); 
	}else{
		//console.info("读卡失败");
	}
	RfExit();//关闭设备
	//DevBeep();//蜂鸣器蜂鸣
	//return cardNo;
	return result;
}

//写企业ID号
function cardIssuers(eId,oNo,cId){
	var result=false;
	
	keyMode="0";
	secin="1";
	
	RfInit();//打开设备
	var cardNo=RfCard();//寻卡
	if(cardNo){
		RfLoadkey();//装载密钥 
		RfAuthentication();//核对密码
		if(WriteFactoryID(parseInt(eId))){
			if(WriteOrgCode(oNo)){
				if(WriteControlerID(parseInt(cId))){
//					console.info("Write_cId_Fail!");  
					result=true;
				}else{
					//console.info("Write_cId_Fail!"); 
				}
			}else{
				//console.info("Write_oNo_Fail!"); 
			}
		}
//		if(!WriteFactoryID(parseInt(eId))){
//			console.info("Write_eId_Fail!");  
//			return false;
//		}
////		if(!WriteOrgCode(parseInt(oNo))){
////			console.info("Write_oNo_Fail!");  
////			return false;
////		}
//		if(WriteControlerID(parseInt(cId))){
//			console.info("Write_cId_Fail!");  
//			result=true;
//		}
		else{
			//console.info("Write_eId_Fail!"); 
		}
	}else{
		//console.info("读卡失败");
	}
	RfExit();//关闭设备
	return result;
}