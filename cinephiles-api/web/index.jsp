<%-- 
    Document   : index
    Created on : Nov 12, 2015, 10:09:44 PM
    Author     : charliearlie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css"
        <title>Cinephiles API</title>
    </head>
    <body>
        <h1>Cinephiles API</h1>

        <h2>Cinephiles API Basic Documentation</h2>

        <h3>Send all data requests to:</h3>
        <pre id="request">https://cinephiles90.herokuapp.com/api/</pre>
        <table border="1" style="background-color:#FFFFFF;border-collapse:collapse;border:1px solid #000000;color:#000000;width:100%" cellpadding="3" cellspacing="5">
            <tr>
                <td><strong>Parameter</strong></td>
                <td><strong>Description</strong></td>
                <td><strong>Example</strong></td>
            </tr>
            <tr>
                <td><pre>media/hotmovies</pre></td>
                <td>Returns JSON array of the hottest movies on cinephiles</td>
                <td></td>
            </tr>
            <tr>
                <td><pre>media/hotseries</pre></td>
                <td>Returns JSON array of the hottest series on cinephiles</td>
                <td></td>
            </tr>
            <tr>
                <td><pre>media/hotgames</pre></td>
                <td>Returns JSON array of the hottest games on cinephiles</td>
                <td></td>
            </tr>
            <tr>
                <td><pre>media/hotbygenre/{genre}/</pre></td>
                <td>Returns JSON array of the hottest movies by specified genre</td>
                <td><pre>media/hotbygenre/comedy</pre></td>
            </tr>
            <tr>
                <td><pre>title/{mediaTitle}<pre></td>
                <td>Returns JSON information of specified media by title. '+' replaces spaces
                as they are not valid in URLs</td>
                <td><pre>title/the+dark+knight</pre></td>
            </tr>
            <tr>
                <td><pre>id/{mediaId}</pre></td>
                <td>Returns JSON information of specified media by imdb ID</td>
                <td><pre>id/tt1663202</pre></td>
            </tr>
            <tr>
                <td><pre>search/{searchParam}</pre></td>
                <td>Returns JSON array of results found from your search query</td>
                <td>search/sound+of+music</td>
            </tr>
        </table>
    </body>
</html>
