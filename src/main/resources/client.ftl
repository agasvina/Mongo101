<html>
  <head>
    <title>Welcome!</title>
  </head>
  <body>
    <h1> What is the meaning of life? ${answer} </h1>
    <form action="/changeValue" method="POST">
      <p>Change the answer</p>
      <input type="text" name="answer"/>
      <input type="submit" value="Submit">
    </form>
  </body>
</html>