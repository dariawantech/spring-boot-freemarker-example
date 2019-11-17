<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>View Note</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    </head>
    <body>
        <h1>View Note</h1>
        <a href="/notes">Back to Note List</a>
        <br/><br/>
        <#if note??>
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${note.id}</td>          
                </tr>
                <tr>
                    <td>Title</td>
                    <td>:</td>
                    <td>${note.title}</td>             
                </tr>
                <tr>
                    <td>Content</td>
                    <td>:</td>
                    <td>${note.content}</td>              
                </tr>
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
            </table>
            <br/><br/>
            <#if allowDelete>
                <form action="${'/notes/' + note.id + '/delete'}" method="POST">
                    Delete this note? <input type="submit" value="Yes" />
                </form>
            <#else>
                <div>
                    <a href="${'/notes/' + note.id + '/edit'}">Edit</a> |
                    <a href="${'/notes/' + note.id + '/delete'}">Delete</a>
                </div>
            </#if>
        </#if>
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>
    </body>
</html>