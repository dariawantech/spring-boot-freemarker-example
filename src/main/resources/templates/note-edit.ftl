<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><#if add>Create a Note<#else>Edit a Note</#if></title>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    </head>
    <body>
        <h1><#if add>Create a Note:<#else>Edit a Note:</#if></h1>
        <a href="/notes">Back to Note List</a>
        <br/><br/>
        <#if add>
            <#assign urlAction>/notes/add</#assign>
            <#assign submitTitle>Create</#assign>
        <#else>
            <#assign urlAction>${'/notes/' + note.id + '/edit'}</#assign>
            <#assign submitTitle>Update</#assign>
        </#if>
        <form action="${urlAction}" name="note" method="POST">
            <table border="0">
                <#if note.id??>
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${note.id}</td>             
                </tr>
                </#if>
                <tr>
                    <td>Title</td>
                    <td>:</td>
                    <td><input type="text" name="title" value="${(note.title)!''}" /></td>              
                </tr>
                <tr>
                    <td>Content</td>
                    <td>:</td>
                    <td><textarea name="content" rows="4" cols="50">${(note.content)!""}</textarea></td>
                </tr>
                <#if note.createdOn??>
                <tr>
                    <td>Created On</td>
                    <td>:</td>
                    <td>${(note.createdOn).format('yyyy-MM-dd HH:mm:ss')}</td>              
                </tr>
                <tr>
                    <td>Updated On</td>
                    <td>:</td>
                    <td>${(note.updatedOn).format('yyyy-MM-dd HH:mm:ss')}</td>              
                </tr>
                </#if>
            </table>
            <input type="submit" value="${submitTitle}" />
        </form>

        <br/>
        <!-- Check if errorMessage is not null and not empty -->       
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>       
    </body>
</html>