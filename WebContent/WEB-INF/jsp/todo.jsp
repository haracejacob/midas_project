<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sadang Todo</title>

    <link href="/midasWeb/js/node_modules/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/midasWeb/js/node_modules/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/midasWeb/less/style.less" type="text/css" rel="stylesheet/less">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div id="wrapper">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper" class="sadang-content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-10">
                    <input type="text" class="form-control add-todo-js" id="exampleInputAmount" placeholder="insert new task...">
                </div>
                <div class="col-xs-2">
                    <button type="submit" class="btn btn-primary sadang-btn add-todo-btn-js">추가</button>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <p class="sharing-code" style="margin: 10px;"></p>
                </div>
            </div>
            <div class="total-todolist">
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->
</div>
<!-- /#wrapper -->

    <!-- Modal -->
    <div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
    <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <h4 class="modal-title">New Category</h4>
    </div>
    <div class="modal-body">
        <form role="form">
        <div class="form-group">
            <label class="radio-inline"><input id="addNewCategory" type="radio" name="optradio" checked>New</label>
            <label class="radio-inline"><input id="addSharedCategory" type="radio" name="optradio">Sharing Code</label>
        </div>
        <div class="form-group">
            <input type="text" id="addCategoryText" class="form-control">
        </div>
        </form>
    </div>
    <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-default btn-primary sadang-btn add-category-js">만들기</button>
    </div>
    </div>

    </div>
    </div>

<script src="/midasWeb/js/node_modules/less/dist/less.min.js" type="text/javascript"></script>
<script src="/midasWeb/js/node_modules/jquery/dist/jquery.min.js" type="text/javascript"></script>
<script src="/midasWeb/js/node_modules/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/midasWeb/js/node_modules/handlebars/dist/handlebars.min.js" type="text/javascript"></script>
<script src="/midasWeb/js/node_modules/moment/min/moment.min.js" type="text/javascript"></script>
<script src="/midasWeb/js/node_modules/underscore/underscore-min.js" type="text/javascript"></script>
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    jQuery.fn.putCursorAtEnd = function() {
        return this.each(function() {
        $(this).focus()
        // If this function exists...
        if (this.setSelectionRange) {
        // ... then use it (Doesn't work in IE)
        // Double the length because Opera is inconsistent about whether a carriage return is one character or two. Sigh.
        var len = $(this).val().length * 2;
        this.setSelectionRange(len, len);
        } else {
        // ... otherwise replace the contents with itself
        // (Doesn't work in Google Chrome)
        $(this).val($(this).val());
        }
        // Scroll to the bottom, in case we're in a tall textarea
        // (Necessary for Firefox and Google Chrome)
        this.scrollTop = 999999;
        });
    };
    Handlebars.registerHelper('active', function(a, b, options) {
        if(a==b)
            return "active";
        else
            return "";
    });

    Handlebars.registerHelper('icon', function(val, block) {
        switch(val){
            case "Bookmark" : return "fa-star";
            case "Done" : return "fa-thumbs-up";
            default : return "fa-list-ul";
        }
    });

    Handlebars.registerHelper('starred', function(conditional, options) {
        if(conditional == 1)
            return "starred";
        else
            return "";
    });

    Handlebars.registerHelper('completed', function(conditional, options) {
        if(conditional == 1)
        return "completed";
        else
        return "";
    });
</script>
<script src="/midasWeb/js/src/Store.js" type="text/javascript"></script>
<script src="/midasWeb/js/src/TodoApi.js" type="text/javascript"></script>
<script src="/midasWeb/js/src/CategoryApi.js" type="text/javascript"></script>
<script src="/midasWeb/js/todo.js" type="text/javascript"></script>

<script id="category-template" type="text/x-handlebars-template">
    <li class="sidebar-brand">
    <a href="#">
        Sadang
    </a>
    <a class="settings" href="logout">sign out</a>
    </li>

    <li>
    <a class="{{active 'Total' currentCategory}} go-to-category-js" data-category="Total" href="#"><span class="icon"><i class="fa fa-list-ul fa-1" aria-hidden="true"></i></span><span class="category">Total</span></a>
    </li>
    {{#each data}}
        <li>
        <a class="{{active category_name ../currentCategory}} go-to-category-js" data-seq={{category_seq}} data-category={{category_name}} href="#"><span class="icon"><i class="fa {{icon category_name}} fa-1" aria-hidden="true"></i></span><span class="category">{{category_name}}</span></a>
        </li>
    {{/each}}

    <li>
    <a class="add-category add-category-modal-js" href="#"><i class="fa fa-plus fa-1" aria-hidden="true"></i></a>
    </li>

    </script>

<script id="todolist-template" type="text/x-handlebars-template">
<div class="row sadang-todolist">
<div class="col-xs-12">
{{#each data}}
<h2 class="today">{{date.dayOfWeek}}</h2>
<p class="details">{{date.fullDate}}</p>
<ul class="todo">
    {{#each todoList}}
    <li class="clearfix {{completed complete}}">
    <div class="content">{{contents}}</div>
    <input type="hidden" id="todoId" value="{{todo_seq}}">
    <input type="hidden" id="bookmark" value="{{bookmark}}">
    <input type="hidden" id="complete" value="{{complete}}">
    <div class="status-wrapper status-wrapper-js">
    <div class="status is-Transformed" data-toggle="tooltip" title="completed?"></div>
    </div>
    <div class="trash-wrapper trash-wrapper-js"><i class="fa fa-trash fa-2x" aria-hidden="true" data-toggle="tooltip" title="delete?"></i>
    </div>
    <div class="star-wrapper star-wrapper-js {{starred bookmark}}"><i class="fa fa-star fa-2x" aria-hidden="true" data-toggle="tooltip" title="bookmark?"></i>
    </div>
    </li>
	<hr>
    {{/each}}
</ul>
{{/each}}
</div>
</div>
</script>
</body>
</html>
