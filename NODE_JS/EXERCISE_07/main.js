const express = require('express');
const fs = require('fs');
const {StringDecoder} = require('string_decoder');
const decoder = new StringDecoder('utf8');
let app = express();
const PORT = process.env.PORT || 3000;
const fileNames = ['lorem', 'marley','pinkfloyd','starwars'];

// root info
app.get('/', (req,res) => {
  const message = "<p> Welcome to Exercise 7! <br/> You can go to <b>/getTime</b> to get current time.<br/> You can go to <b>/getFile?filename={name}</b> to get some files!</p>";
  const fileList = "<b>file List</b><ul><li>lorem</li><li>starwars</li><li>pinkfloyd</li><li>marley</li></ul>";
  res.set('Content-Type', 'text/html');
  res.status(200).send(message+fileList);
  return;
})

// get Time from system
app.get('/getTime', (req,res) => {
  let date = new Date(Date.now()).toTimeString();
  res.status(200).send(`The Current Time Is : ${date}`);
  return;
});

// get file from server
app.get('/getFile', (req,res) => {
  const filename = req.query.filename;
  // check if file exists
  if(fileNames.indexOf(filename)===-1){
    res.status(404).send('no such file! go back to root for file names');
    return;
  }
  // start reading file
  fs.readFile(`files/${filename}.txt`, (err, data) => {
    // if fail, send error
    if(err){
      console.log(err);
      res.status(403).send('Failed to get file');
      return;
    }
    let parsedText = "<p><b>" + decoder.write(data).replace(/\n/g,"<br/>").replace(/ /g,"&nbsp;&nbsp;") + "</b></p>";
    res.set('Content-Type', 'text/html');
    res.status(200).send(parsedText);
  });
});

app.listen(PORT, () => {
  console.log(`listening at port ${PORT}`);
})
