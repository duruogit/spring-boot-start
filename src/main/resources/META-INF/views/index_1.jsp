<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    String version = "v2";
%>
<%@ include file="global_variate.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商户后台系统</title>
    <link href="${ctx}/css/bootstrap.min.css?v=1.6" rel="stylesheet">
    <link href="${ctx}/font-awesome/css/font-awesome.css?v=1.6" rel="stylesheet">
    <!-- Morris -->
    <link href="${ctx}/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <!-- Gritter -->
    <link href="${ctx}/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <link href="${ctx}/css/animate.css" rel="stylesheet">
    <link href="${ctx}/css/style.css?v=1.6" rel="stylesheet">
</head>
<body>
<!--     <div id="wrapper"> -->
<%--         <%@ include file="navbar_side.jsp"%> --%>
<!--         <div id="page-wrapper" class="gray-bg dashbard-1"> -->
<%--         	<%@ include file="navbar_top.jsp"%> --%>
            <div class="row border-bottom white-bg dashboard-header">
                <div class="col-sm-3">
                    <h2>Hello,Beaut-zihan</h2>
                    <small>您有32条消息和6个待处理事项</small>
                    <ul class="list-group clear-list m-t">
                        <li class="list-group-item fist-item">
                            <span class="pull-right">
                                    09:00
                                </span>
                            <span class="label label-success">1</span> 请联系我
                        </li>
                        <li class="list-group-item">
                            <span class="pull-right">
                                    10:16
                                </span>
                            <span class="label label-info">2</span> 签订合同
                        </li>
                        <li class="list-group-item">
                            <span class="pull-right">
                                    08:22
                                </span>
                            <span class="label label-primary">3</span> 新分店开张
                        </li>
                        <li class="list-group-item">
                            <span class="pull-right">
                                    11:06
                                </span>
                            <span class="label label-default">4</span> 打电话给璟雯
                        </li>
                        <li class="list-group-item">
                            <span class="pull-right">
                                    12:00
                                </span>
                            <span class="label label-primary">5</span> 发邮件给国民岳父
                        </li>
                    </ul>
                </div>
                <div class="col-sm-6">
                    <div class="flot-chart dashboard-chart">
                        <div class="flot-chart-content" id="flot-dashboard-chart"></div>
                    </div>
                    <div class="row text-left">
                        <div class="col-xs-4">
                            <div class=" m-l-md">
                                <span class="h4 font-bold m-t block">&yen; 406,100</span>
                                <small class="text-muted m-b block">销售营销报告</small>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <span class="h4 font-bold m-t block">&yen; 150,401</span>
                            <small class="text-muted m-b block">年销售收入</small>
                        </div>
                        <div class="col-xs-4">
                            <span class="h4 font-bold m-t block">&yen; 16,822</span>
                            <small class="text-muted m-b block">半年收入利润率</small>
                        </div>

                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="statistic-box">
                        <h4>
                            项目的进度
                        </h4>
                        <p>
                            您还有两项未完成的任务
                        </p>
                        <div class="row text-center">
                            <div class="col-lg-6">
                                <div class="chart easypiechart inline" data-percent="73"><span class="easypie-text">61%</span>
                                </div>
                                <h5>OA测</h5>
                            </div>
                            <div class="col-lg-6">
                                <div class="chart2 easypiechart inline" data-percent="33"><span class="easypie-text">31%</span>
                                </div>
                                <h5>CRM</h5>
                            </div>
                        </div>
                        <div class="m-t">
                            <small>请尽快完成相关项目的开发、测试工作</small>
                        </div>

                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="wrapper wrapper-content">
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <h5>数据报告</h5>  <span class="label label-primary">K+</span>
                                        <div class="ibox-tools">
                                            <a class="collapse-link">
                                                <i class="fa fa-chevron-up"></i>
                                            </a>
                                            <a class="dropdown-toggle" data-toggle="dropdown" href="index.html#">
                                                <i class="fa fa-wrench"></i>
                                            </a>
                                            <ul class="dropdown-menu dropdown-user">
                                                <li><a href="index.html#">选项1</a>
                                                </li>
                                                <li><a href="index.html#">选项2</a>
                                                </li>
                                            </ul>
                                            <a class="close-link">
                                                <i class="fa fa-times"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="ibox-content">
                                        <div>

                                            <div class="pull-right text-right">

                                                <span class="bar_dashboard">5,3,9,6,5,9,7,3,5,2,4,7,3,2,7,9,6,4,5,7,3,2,1,0,9,5,6,8,3,2,1</span>
                                                <br/>
                                                <small class="font-bold">&yen; 20 054.43</small>
                                            </div>
                                            <h4>广东省销售数据
                                            <br/>
                                            <small class="m-r"><a href="graph_flot.html"> 查看所有数据</a> </small>
                                        </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <h5>最新动态</h5>
                                        <div class="ibox-tools">
                                            <a class="collapse-link">
                                                <i class="fa fa-chevron-up"></i>
                                            </a>
                                            <a class="dropdown-toggle" data-toggle="dropdown" href="index.html#">
                                                <i class="fa fa-wrench"></i>
                                            </a>
                                            <ul class="dropdown-menu dropdown-user">
                                                <li><a href="index.html#">选项1</a>
                                                </li>
                                                <li><a href="index.html#">选项2</a>
                                                </li>
                                            </ul>
                                            <a class="close-link">
                                                <i class="fa fa-times"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="ibox-content no-padding">
                                        <ul class="list-group">
                                            <li class="list-group-item">
                                                <p><a class="text-info" href="index.html#">#感谢有你#</a> 感谢你们一路的相伴，未来也请让我为你们撑腰，我们会更好</p>
                                                <small class="block text-muted"><i class="fa fa-clock-o"></i> 1分钟前</small>
                                            </li>
                                            <li class="list-group-item">
                                                <p><a class="text-info" href="index.html#">@颜文字君</a> 女生身高×1.09后，就是最适合你的男生身高；相反，男生是÷1.09就可以了..小伙伴们可以试着算下..【图是我的..(*/ω＼*)</p>
                                                <div class="text-center m">
                                                    <span id="sparkline8"></span>
                                                </div>
                                                <small class="block text-muted"><i class="fa fa-clock-o"></i> 2小时前</small>
                                            </li>

                                            <li class="list-group-item">
                                                <p><a class="text-info" href="index.html#">#发型师#</a> 刚才剪发，顾客在看这个视频，妈蛋，这舞姿太销魂了，笑得手颤抖。。。</p>
                                                <small class="block text-muted"><i class="fa fa-clock-o"></i> 1分钟前</small>
                                            </li>
                                            <li class="list-group-item">
                                                <p><a class="text-info" href="index.html#">#一年级#</a> ——#陈氏父子# cut：“他是我的陈爸爸”[心]“我叫陈思成，陈老师的陈” [心]“不再见就是，你也好，爸爸也好，妈妈也好，都永远不要说再见</p>
                                                <small class="block text-muted"><i class="fa fa-clock-o"></i> 2分钟前</small>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <h5>最新消息</h5>
                                        <div class="ibox-tools">
                                            <span class="label label-warning-light">10条未读</span>
                                        </div>
                                    </div>
                                    <div class="ibox-content">

                                        <div>
                                            <div class="feed-activity-list">

                                                <div class="feed-element">
                                                    <a href="profile.html" class="pull-left">
                                                        <img alt="image" class="img-circle" src="img/profile.jpg">
                                                    </a>
                                                    <div class="media-body ">
                                                        <small class="pull-right">5分钟前</small>
                                                        <strong>谨斯里</strong> 上传了一个文件
                                                        <br>
                                                        <small class="text-muted">2014.11.8 12:22</small>

                                                    </div>
                                                </div>

                                                <div class="feed-element">
                                                    <a href="profile.html" class="pull-left">
                                                        <img alt="image" class="img-circle" src="img/a2.jpg">
                                                    </a>
                                                    <div class="media-body ">
                                                        <small class="pull-right">2个月前</small>
                                                        <strong>田亮</strong> 参加了<strong>《粑粑去哪儿》</strong> 
                                                        <br>
                                                        <small class="text-muted">2014.11.8 12:22</small>
                                                    </div>
                                                </div>
                                                <div class="feed-element">
                                                    <a href="profile.html" class="pull-left">
                                                        <img alt="image" class="img-circle" src="img/a3.jpg">
                                                    </a>
                                                    <div class="media-body ">
                                                        <small class="pull-right">2小时前</small>
                                                        <strong>林依晨Ariel</strong> 刚刚起床
                                                        <br>
                                                        <small class="text-muted">2014.11.8 12:22</small>
                                                    </div>
                                                </div>
                                                <div class="feed-element">
                                                    <a href="profile.html" class="pull-left">
                                                        <img alt="image" class="img-circle" src="img/a5.jpg">
                                                    </a>
                                                    <div class="media-body ">
                                                        <small class="pull-right">32分钟前</small>
                                                        <strong>颜文字君</strong> 评论了
                                                        <br>
                                                        <small class="text-muted">2014.11.8 12:22</small>
                                                        <div class="well">
                                                            【九部令人拍案叫绝的惊悚悬疑剧情佳作】如果你喜欢《迷雾》《致命ID》《电锯惊魂》《孤儿》《恐怖游轮》这些好片，那么接下来推荐的9部同类题材并同样出色的的电影，绝对不可错过哦~
                                                        </div>
                                                        <div class="pull-right">
                                                            <a class="btn btn-xs btn-white"><i class="fa fa-thumbs-up"></i> 喜欢 </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <button class="btn btn-primary btn-block m-t"><i class="fa fa-arrow-down"></i> 加载更多</button>

                                        </div>

                                    </div>
                                </div>

                            </div>
                            <div class="col-lg-4">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <h5>项目进度</h5>
                                        <div class="ibox-tools">
                                            <a class="collapse-link">
                                                <i class="fa fa-chevron-up"></i>
                                            </a>
                                            <a class="dropdown-toggle" data-toggle="dropdown" href="index.html#">
                                                <i class="fa fa-wrench"></i>
                                            </a>
                                            <ul class="dropdown-menu dropdown-user">
                                                <li><a href="index.html#">选项1</a>
                                                </li>
                                                <li><a href="index.html#">选项2</a>
                                                </li>
                                            </ul>
                                            <a class="close-link">
                                                <i class="fa fa-times"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="ibox-content ibox-heading">
                                        <h3>还有约79842492229个Bug需要修复</h3>
                                        <small><i class="fa fa-map-marker"></i> 地点当然是在办公室</small>
                                    </div>
                                    <div class="ibox-content timeline">

                                        <div class="timeline-item">
                                            <div class="row">
                                                <div class="col-xs-3 date">
                                                    <i class="fa fa-briefcase"></i>
                                                    6:00
                                                    <br/>
                                                    <small class="text-navy">2 小时前</small>
                                                </div>
                                                <div class="col-xs-7 content no-top-border">
                                                    <p class="m-b-xs"><strong>服务器已彻底崩溃</strong>
                                                    </p>

                                                    <p>还未检查出错误</p>

                                                    <p><span data-diameter="40" class="updating-chart">5,3,9,6,5,9,7,3,5,2,5,3,9,6,5,9,4,7,3,2,9,8,7,4,5,1,2,9,5,4,7,2,7,7,3,5,2</span>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="timeline-item">
                                            <div class="row">
                                                <div class="col-xs-3 date">
                                                    <i class="fa fa-file-text"></i>
                                                    7:00
                                                    <br/>
                                                    <small class="text-navy">3小时前</small>
                                                </div>
                                                <div class="col-xs-7 content">
                                                    <p class="m-b-xs"><strong>修复了0.5个bug</strong>
                                                    </p>
                                                    <p>重启服务</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="timeline-item">
                                            <div class="row">
                                                <div class="col-xs-3 date">
                                                    <i class="fa fa-coffee"></i>
                                                    8:00
                                                    <br/>
                                                </div>
                                                <div class="col-xs-7 content">
                                                    <p class="m-b-xs"><strong>喝水、上厕所、做测试</strong>
                                                    </p>
                                                    <p>
                                                        喝了4杯水，上了3次厕所，控制台输出出2324个错误，神啊，带我走吧
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="timeline-item">
                                            <div class="row">
                                                <div class="col-xs-3 date">
                                                    <i class="fa fa-phone"></i>
                                                    11:00
                                                    <br/>
                                                    <small class="text-navy">21小时前</small>
                                                </div>
                                                <div class="col-xs-7 content">
                                                    <p class="m-b-xs"><strong>项目经理打电话来了</strong>
                                                    </p>
                                                    <p>
                                                        TMD，项目经理居然还没有起床！！！
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="timeline-item">
                                            <div class="row">
                                                <div class="col-xs-3 date">
                                                    <i class="fa fa-user-md"></i>
                                                    09:00
                                                    <br/>
                                                    <small>21小时前</small>
                                                </div>
                                                <div class="col-xs-7 content">
                                                    <p class="m-b-xs"><strong>开会</strong>
                                                    </p>
                                                    <p>
                                                        开你妹的会，老子还有897894个bug没有修复
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="timeline-item">
                                            <div class="row">
                                                <div class="col-xs-3 date">
                                                    <i class="fa fa-comments"></i>
                                                    12:50
                                                    <br/>
                                                    <small class="text-navy">讨论</small>
                                                </div>
                                                <div class="col-xs-7 content">
                                                    <p class="m-b-xs"><strong>…………</strong>
                                                    </p>
                                                    <p>
                                                        又是操蛋的一天
                                                    </p>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

<!--         </div> -->
<!--     </div> -->

    <!-- Mainly scripts -->
    <script src="${ctx}/js/jquery-1.10.2.js"></script>
    <script src="${ctx}/js/bootstrap.min.js?v=1.6"></script>
    <script src="${ctx}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${ctx}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Flot -->
    <script src="${ctx}/js/plugins/flot/jquery.flot.js"></script>
    <script src="${ctx}/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="${ctx}/js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="${ctx}/js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="${ctx}/js/plugins/flot/jquery.flot.pie.js"></script>

    <!-- Peity -->
    <script src="${ctx}/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="${ctx}/js/demo/peity-demo.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${ctx}/js/hplus.js?v=1.6"></script>
    <script src="${ctx}/js/plugins/pace/pace.min.js"></script>

    <!-- jQuery UI -->
    <script src="${ctx}/js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <!-- GITTER -->
    <script src="${ctx}/js/plugins/gritter/jquery.gritter.min.js"></script>

    <!-- EayPIE -->
    <script src="${ctx}/js/plugins/easypiechart/jquery.easypiechart.js"></script>

    <!-- Sparkline -->
    <script src="${ctx}/js/plugins/sparkline/jquery.sparkline.min.js"></script>

    <!-- Sparkline demo data  -->
    <script src="${ctx}/js/demo/sparkline-demo.js"></script>

    <script>
        $(document).ready(function () {
            WinMove();
            setTimeout(function () {
                $.gritter.add({
                    title: '您有2条未读信息',
                    text: '请前往<a href="mailbox.html" class="text-warning">收件箱</a>查看今日任务',
                    time: 10000
                });
            }, 2000);


            $('.chart').easyPieChart({
                barColor: '#f8ac59',
                //                scaleColor: false,
                scaleLength: 5,
                lineWidth: 4,
                size: 80
            });

            $('.chart2').easyPieChart({
                barColor: '#1c84c6',
                //                scaleColor: false,
                scaleLength: 5,
                lineWidth: 4,
                size: 80
            });

            var data1 = [
                [0, 4], [1, 8], [2, 5], [3, 10], [4, 4], [5, 16], [6, 5], [7, 11], [8, 6], [9, 11], [10, 30], [11, 10], [12, 13], [13, 4], [14, 3], [15, 3], [16, 6]
            ];
            var data2 = [
                [0, 1], [1, 0], [2, 2], [3, 0], [4, 1], [5, 3], [6, 1], [7, 5], [8, 2], [9, 3], [10, 2], [11, 1], [12, 0], [13, 2], [14, 8], [15, 0], [16, 0]
            ];
            $("#flot-dashboard-chart").length && $.plot($("#flot-dashboard-chart"), [
                data1, data2
            ], {
                series: {
                    lines: {
                        show: false,
                        fill: true
                    },
                    splines: {
                        show: true,
                        tension: 0.4,
                        lineWidth: 1,
                        fill: 0.4
                    },
                    points: {
                        radius: 0,
                        show: true
                    },
                    shadowSize: 2
                },
                grid: {
                    hoverable: true,
                    clickable: true,
                    tickColor: "#d5d5d5",
                    borderWidth: 1,
                    color: '#d5d5d5'
                },
                colors: ["#1ab394", "#464f88"],
                xaxis: {},
                yaxis: {
                    ticks: 4
                },
                tooltip: false
            });
        });
    </script>

    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

</body>

</html>
