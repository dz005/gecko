/**
 * 全局通用JS
 * author:zhi.deng
 * date: 2018-06-04
 * 依赖插件:jquery,bootstrap,layer
 */

//popover
$("[data-toggle='popover']").popover();

// tooltips
$("[data-toggle='tooltip']").tooltip({
    placement: "auto",
    trigger: "hover"
});

//折叠ibox
$('.collapse-link').click(function () {
    var ibox = $(this).closest('div.ibox');
    var button = $(this).find('i');
    var content = ibox.find('div.ibox-content');
    content.slideToggle(200);
    button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
    ibox.toggleClass('').toggleClass('border-bottom');
    setTimeout(function () {
        ibox.resize();
        ibox.find('[id^=map-]').resize();
    }, 50);
});

//关闭ibox
$('.close-link').click(function () {
    var content = $(this).closest('div.ibox');
    content.remove();
});