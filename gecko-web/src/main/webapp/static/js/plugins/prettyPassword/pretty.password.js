/**
 * 密码字段渲染插件
 * author:zhi.deng
 * date: 2018-03-06
 */
( function ($) {
    $.fn.extend({
        prettyPassword: function (options) {
            var defaults = {};
            var options = $.extend(defaults, options);
            var plugin = this;

            function make_form($el, options) {
                $el.wrap('<div class="input-group"></div>');
                $el.after('\
                <span class="input-group-addon" style="cursor: pointer;" title="显示">\
                    <i class="fa fa-eye"></i>\
                </span>\
                ');

                return $el.parent();
            };

            function bind_button($wrap) {
                var $input = $wrap.find('input[type="password"]');
                $wrap.find(".input-group-addon").click(function (e) {
                    var $this = $(this);
                    var $i = $this.find("i");
                    if ($i.hasClass("fa-eye")) {
                        $i.removeClass("fa-eye").addClass("fa-eye-slash");
                        $this.attr("title", "隐藏");
                        $input.attr("type", "text");
                    } else {
                        $i.removeClass("fa-eye-slash").addClass("fa-eye");
                        $this.attr("title", "显示");
                        $input.attr("type", "password");
                    }
                });
            };
            return plugin.each(function () {
                var $this = $(this);

                if ($this) {
                    var $wrap = make_form($this, options);
                    bind_button($wrap);
                }
            });
        }
    });
}(jQuery));