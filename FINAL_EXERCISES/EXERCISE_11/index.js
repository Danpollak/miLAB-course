const express = require('express');
const MongoClient = require('mongodb').MongoClient;
const bodyParser = require('body-parser');
const MONGO_URL = 'mongodb://admin:admin@ds251827.mlab.com:51827/music';
let server = express();
const PORT = process.env.PORT || 3000;
let connectedDB = false;
server.use(bodyParser.json());

const createEntry = (db,req, res) => {
  const entry = req.body.entry;
  if(!entry){
    res.status(400).send('Did not get input');
    return;
  }
  let database = db.db('music');
  let collection = database.collection('songs');
  collection.insertOne(entry,
  (err, cb) => {
    if(err){
      console.log('Could not create entry');
      res.status(503).send('write failed');
      return;
    }
    res.status(200).send('entry created');
  });
}

const readEntry = (db, req, res) => {
  const findKey = req.body.findKey;
  const value = req.body.value;
  if(!findKey || !value){
    res.status(400).send('Did not get input');
    return;
  }
  let database = db.db('music');
  let collection = database.collection('songs');
  collection.find({[findKey] : value}).toArray(
  (err, result) => {
    if(err){
      console.log('could not preform find');
      res.status(503).send('find failed');
      return;
    }
    res.status(200).send(JSON.stringify(result));
  });
}

const updateEntry = (db, req, res) => {
  const entry = req.body.entry;
  const updatedValue = req.body.updated;
  if(!entry || !updatedValue){
    res.status(400).send('Did not get input');
    return;
  }
  let database = db.db('music');
  let collection = database.collection('songs');
  collection.updateOne(entry, {$set : updatedValue},
  (err, cb) => {
    if(err){
      console.log('Could not update entry');
      res.status(503).send('update failed');
      return;
    }
    res.status(200).send('entry updated');
  });
}

const deleteEntry = (db, req, res) => {
  const entry = req.body.entry;
  if(!entry){
    res.status(400).send('Did not get input');
    return;
  }
  let database = db.db('music');
  let collection = database.collection('songs');
  collection.deleteOne(entry,
  (err, cb) => {
    if(err){
      console.log('Could not delete entry');
      res.status(503).send('delete failed');
      return;
    }
    res.status(200).send('entry deleted');
  });
}

const runServer = (db) => {
  server.put('/create', (req, res) => {
    createEntry(db,req, res);
  });

  server.post('/read', (req, res) => {
    readEntry(db,req, res);
  });

  server.post('/update', (req, res) => {
    updateEntry(db,req, res);
  });

  server.post('/delete', (req, res) => {
    deleteEntry(db,req, res);
  });
}

const connectMongoServer = () => {
  console.log('Trying to connect..');
  MongoClient.connect(MONGO_URL, (err, db) => {
      if(err){
        console.log('Could not connect to database. Trying again..');
        console.log(err);
        connectMongoServer();
      }
      else {
        console.log('Connected to database!');
        runServer(db);
      }
    });
}

server.listen(PORT, () => {
  console.log(`listening in port ${PORT}`);
  connectMongoServer();
});
