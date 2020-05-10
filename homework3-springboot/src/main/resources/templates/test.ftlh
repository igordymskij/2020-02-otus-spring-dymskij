<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Тест</title>
    </head>
    <body>
        <#list questions as question>
            <div>
                <span>${question}</span>
            </div>
                <#else>
                No questions
            </#list>
    </body>
</html>