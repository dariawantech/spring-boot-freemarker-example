<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Note List</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    </head>
    <body>
        <h1>Note List</h1>
        
        <div>
            <nobr>
                <a href="/notes/add">Add Note</a> |
                <a href="/">Back to Index</a>
            </nobr>
        </div>
        <br/><br/>
        <div>
            <table border="1">
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Created On</th>
                    <th>Updated On</th>
                    <th>Edit</th>                    
                </tr>
                <#list notes as note>
                    <tr>
                        <td><a href="${'notes/' + note.id}">${note.id}</a></td>
                        <td><a href="${'notes/' + note.id}">${note.title}</a></td>
                        <td>${note.shortContent}</td>
                        <td>${(note.createdOn).format('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${(note.updatedOn).format('yyyy-MM-dd HH:mm:ss')}</td>
                        <td><a href="${'notes/' + note.id + '/edit'}">Edit</a></td>
                    </tr>
                </#list>
            </table>          
        </div>
        <br/><br/>
        <div>
            <nobr>
                <#if hasPrev><a href="${'notes?page=' + prev}">Prev</a>&nbsp;&nbsp;&nbsp;</#if>
                <#if hasNext><a href="${'notes?page=' + next}">Next</a></#if>
            </nobr>
        </div>
    </body>
</html>