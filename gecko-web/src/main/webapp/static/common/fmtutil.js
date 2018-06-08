 //扩展Date的format方法
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        }
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    }
    /**
    *转换日期对象为日期字符串
    * @param date 日期对象
    * @param isFull 是否为完整的日期数据,
    *               为true时, 格式如"2000-03-05 01:05:04"
    *               为false时, 格式如 "2000-03-05"
    * @return 符合要求的日期字符串
    */
    function getSmpFormatDate(date, isFull) {
        var pattern = "";
        if (isFull == true || isFull == undefined) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        } else {
            pattern = "yyyy-MM-dd";
        }
        return getFormatDate(date, pattern);
    }
    /**
    *转换当前日期对象为日期字符串
    * @param date 日期对象
    * @param isFull 是否为完整的日期数据,
    *               为true时, 格式如"2000-03-05 01:05:04"
    *               为false时, 格式如 "2000-03-05"
    * @return 符合要求的日期字符串
    */

    function getSmpFormatNowDate(isFull) {
        return getSmpFormatDate(new Date(), isFull);
    }
    /**
    *转换long值为日期字符串
    * @param l long值
    * @param isFull 是否为完整的日期数据,
    *               为true时, 格式如"2000-03-05 01:05:04"
    *               为false时, 格式如 "2000-03-05"
    * @return 符合要求的日期字符串
    */

    function getSmpFormatDateByLong(l, isFull) {
        return getSmpFormatDate(new Date(l), isFull);
    }
    /**
    *转换long值为日期字符串
    * @param l long值
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss
    * @return 符合要求的日期字符串
    */

    function getFormatDateByLong(l, pattern) {
        return getFormatDate(new Date(l), pattern);
    }
    /**
    *转换日期对象为日期字符串
    * @param l long值
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss
    * @return 符合要求的日期字符串
    */
    function getFormatDate(date, pattern) {
        if (date == undefined) {
            date = new Date();
        }
        if (pattern == undefined) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }
        return date.format(pattern);
    }

    function getDateDesc(dateTimeStamp){
    	  if (dateTimeStamp == undefined) {
    		  return "无";
    	  }
    	var minute = 1000 * 60;
    	var hour = minute * 60;
    	var day = hour * 24;
    	var halfamonth = day * 15;
    	var month = day * 30;
    	var now = new Date().getTime();
    	var diffValue = now - dateTimeStamp;
    	if(diffValue < 0){return;}
    	var monthC =diffValue/month;
    	var weekC =diffValue/(7*day);
    	var dayC =diffValue/day;
    	var hourC =diffValue/hour;
    	var minC =diffValue/minute;
    	if(monthC>=1){
    		result="" + parseInt(monthC) + "月"+ (parseInt(dayC)-30*parseInt(monthC)) +"天前";
    	}
    	else if(dayC>=1){
    		result=""+ parseInt(dayC) +"天"+ (parseInt(hourC)-24*parseInt(dayC)) +"时前";
    	}
    	else if(hourC>=1){
    		result=""+ parseInt(hourC) +"时"+(parseInt(minC)-60*parseInt(hourC)) +"分前";
    	}
    	else if(minC>=1){
    		result=""+ parseInt(minC) +"分钟前";
    	}else{
    		result="刚刚";
    	}
    	console.log("转换时间:"+getSmpFormatDateByLong(dateTimeStamp,true) +"=>" +result);
    	return result;
    }