<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${appHeader}</title>
    </head>
    <body>
        <main>
            <p>${appRole}</p>
            <form action="http://localhost:8080/answer" method="post" enctype="multipart/form-data">
                <fieldset>
                    <#list questions as question>
                        <#list question as item>
                            <p>${item}</p>
                        </#list>
                            <p>
                                <label>*${userAnswer}
                                    <input type="text" name="${question? index}" required pattern="[1-4]" size="1">
                                </label>
                            </p>
                    </#list>
                </fieldset>

                <input type="submit" value=${sendButton}>

                <p>
                    <small>*${appFieldWarning}</small>
                </p>
             </form>
        </main>
    </body>
</html>