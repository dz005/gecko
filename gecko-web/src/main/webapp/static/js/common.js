/**
 * 全局通用JS
 * author:zhi.deng
 * date: 2018-06-04
 * 依赖插件:jquery,bootstrap,layer
 */

//animation.css
function animationHover(element, animation) {
    element = $(element);
    element.hover(
        function () {
            element.addClass('animated ' + animation);
        },
        function () {
            //动画完成之前移除class
            window.setTimeout(function () {
                element.removeClass('animated ' + animation);
            }, 2000);
        });
}

//拖动面板
function WinMove() {
    var element = "[class*=col]";
    var handle = ".ibox-title";
    var connect = "[class*=col]";
    $(element).sortable({
        handle: handle,
        connectWith: connect,
        tolerance: 'pointer',
        forcePlaceholderSize: true,
        opacity: 0.8,
    }).disableSelection();
};


//设置Switchery开关
function setSwitchery(switchElement, checked) {
    var clicked = (checked && !switchElement.isChecked()) || (!checked && switchElement.isChecked());
    switchElement.setPosition(clicked);
    switchElement.handleOnchange(true);
}

//赋值Form
function json2Form(ele, data) {
    ele.find("input[name]").each(function () {
        var $this = $(this);
        var name = $(this).attr('name');
        if ($this.attr("type") == "checkbox") {
            if ($this.hasClass("ui-switch")) { //处理开关控件
                $this.attr("checked", data[name] === true);
            } else {
                $this.each(function () {
                    if ($(this).val() in data[name]) {
                        $(this).attr("checked", true);
                    } else {
                        $(this).attr("checked", false);
                    }
                });
            }
        } else if ($this.attr("type") == "radio") {
            if ($this.val() == data[name]) {
                $this.attr("checked", "checked");
            } else {
                $this.attr("checked", "");
            }
        } else {
            $this.val(data[name] != null ? data[name] : "");
        }
    });
    ele.find("select[name],textArea[name]").each(function () {
        var name = $(this).attr('name');
        $(this).val(data[name] != null ? data[name] : "");
    });
}

//提取Form数据
function form2Json(ele) {
    var data = {};
    ele.find("input[name]").each(function () {
        var $this = $(this);
        var $name = $(this).attr('name');
        if ($this.attr("type") == "checkbox") {
            if ($this.hasClass("switch")) { //处理开关控件
                data[$name] = $this.is(":checked");
            } else {
                if ($(this).is(":checked")) {
                    if (data[$name] !== undefined) {
                        data[$name].push($(this).val());
                    } else {
                        data[$name] = [$(this).val()];
                    }
                }
            }
        } else if ($this.attr("type") == "radio") {
            if ($(this).is(":checked")) {
                data[$name] = $(this).val();
            }
        } else {
            if ($(this).val()) {
                data[$name] = $(this).val();
            }
        }
    });
    ele.find("select[name],textArea[name]").each(function () {
        if ($(this).val()) {
            data[$(this).attr('name')] = $(this).val();
        }
    });
    return data;
}

function alertError(msg) {
    layer.open({
        type: 1,
        title: '<i class="fa fa-warning"></i> 操作失败',
        area: ['800px', '400px'],
        content: '<textarea disabled="disabled" style="resize:none;height: 100%" class="form-control">'
        + msg + '</textarea>'
    });
}

//AJAX全局异常处理
$(document).ajaxError(function (event, xhr, settings, infoError) {
    if (parent.layer) {
        parent.layer.closeAll('loading');
    }
    if (layer) {
        layer.closeAll('loading');
        layer.msg("系统异常,请联系管理员", {icon: 5, time: 2000});
    }
});

//表单验证默认设置
if ($.validator) {
    $.validator.setDefaults({
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
        },
        errorPlacement: function (error, element) {
            error.appendTo($(element).closest('.form-group').children(":last-child"));
        },
        errorElement: "span",
        errorClass: "has-error help-block m-b-none",
        validClass: "",
        ignore: ":disabled"
    });
}

function openLayerRight(title, url) {
    layer.open({
        type: 2,
        title: title,
        area: ['70%', '100%'],
        offset: 'rb',
        scrollbar: false,
        shadeClose: true,
        shift: 5,
        closeBtn: 0,
        content: url
    });
}

function openLayerFull(title, url) {
    layer.open({
        type: 2,
        title: title,
        area: ['100%', '100%'],
        scrollbar: false,
        shift: 2,
        content: url
    });
}