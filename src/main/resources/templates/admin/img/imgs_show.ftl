<#include "../layout/layout.ftl">
<@html page_title="轮播图管理" page_tab="imgs_show">
    <section class="content-header">
        <h1>
            轮播图
            <small>管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">轮播图管理</li>
        </ol>
    </section>

    <section class="content">
        <div class="box box-info">
            <div class="box-header with-border">
                <h3 class="box-title">轮播图列表</h3>
            </div>
            <div class="box-body table-responsive">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>标题</th>
                        <th>描述</th>
                        <th>图片</th>
                        <th>跳转链接</th>
                        <th>排序</th>
                        <th>状态</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list carousels as carousels>
                        <tr>
                            <td>${carousels.id}</td>
                            <td>${carousels.title!}</td>
                            <td>${carousels.description!}</td>
                            <td><img src="${carousels.imageUrl}" style="height: 50px;" alt="图片"/></td>
                            <td><a href="${carousels.webUrl!"#"}" target="_blank">${carousels.webUrl}</a></td>
                            <td>${carousels.sort}</td>
                            <td>
                                <#if carousels.isActive?? && carousels.isActive()>
                                    <span class="label label-success">启用</span>
                                <#else>
                                    <span class="label label-danger">禁用</span>
                                </#if>
                            </td>
                            <td>${carousels.createTime?string("yyyy-MM-dd HH:mm")}</td>
                            <td>
                                    <button onclick="editCarousel('${carousels.id}', '${carousels.imageUrl}', '${carousels.webUrl}')" class="btn btn-xs btn-warning">编辑</button>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <!-- 编辑轮播图弹窗 -->
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form id="editForm">
                    <div class="modal-header">
                        <h4 class="modal-title" id="editModalLabel">编辑轮播图</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="carouselId">
                        <div class="form-group">
                            <label>图片地址</label>
                            <input type="text" class="form-control" id="imageUrl" required>
                        </div>
                        <div class="form-group">
                            <label>跳转链接</label>
                            <input type="text" class="form-control" id="webUrl">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        function editCarousel(id, imageUrl, webUrl) {
            $('#carouselId').val(id);
            $('#imageUrl').val(imageUrl);
            $('#webUrl').val(webUrl);
            $('#editModal').modal('show');
        }

        $('#editForm').submit(function (e) {
            e.preventDefault();
            const data = {
                id: $('#carouselId').val(),
                imageUrl: $('#imageUrl').val(),
                webUrl: $('#webUrl').val()
            };

            $.ajax({
                url: '/admin/img/edit',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (res) {
                    if (res.code === 200) {
                        toast("保存成功", "success");
                        setTimeout(function () {
                            location.reload();
                        }, 800);
                    } else {
                        toast("保存失败：" + res.message);
                    }
                }
            });
        });
    </script>
</@html>
