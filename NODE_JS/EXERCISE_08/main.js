const express = require('express');
const fs = require('fs');
const path = require('path');
const PORT = process.env.PORT || 3000;
let server = express();

const availableFiles = {
  trycatch: 'trycatch.jpg',
  javascript: 'javascript.png',
  hardcoding: 'hard-coding.jpg',
  hobby: 'hobby.jpg',
  jesus: 'jesus.jpg',
  nointernet: 'no-internet.jpeg',
  vlc: 'vlc.jpg'
}

server.get("/", (req,res) => {
  res.status(200).sendFile(path.join(__dirname+'/main.html'));
});

server.get("/files/:filename", (req,res) => {
  const filename = req.params.filename;
  if(availableFiles[filename]){
    const readFile = fs.createReadStream(path.join(__dirname+'/files/'+availableFiles[filename]));
    readFile.pipe(res).on('end', (err) => {
      if(err){
        res.status(304).send('failed to load');
      }
      res.status(200);
    });
  } else {
    res.status(404).send(`Sorry, I couldn't find the file ${filename} :(`);
  }
});

server.listen(PORT, () => {
  console.log(`listening at ${PORT}`);
});
