const express = require('express');
const MongoClient = require('mongodb').MongoClient;
const bodyParser = require('body-parser');
const MONGO_URL = 'mongodb://admin:admin@ds251827.mlab.com:51827/music';
let server = express();
const PORT = process.env.PORT || 3000;
server.use(bodyParser.json());

server.put('/create', (req, res) => {
  createEntry(req, res);
});

server.post('/read', (req, res) => {
  readEntry(req, res);
});

server.post('/update', (req, res) => {
  updateEntry(req, res);
});

server.post('/delete', (req, res) => {
  deleteEntry(req, res);
})

const createEntry = (req, res) => {
  const entry = req.body.entry;
  if(!entry){
    res.status(400).send('Did not get input');
    return;
  }
  MongoClient.connect(MONGO_URL, (err, db) => {
    if(err){
      console.log('Could not connect to Database');
      res.status(503).send('could not connect to db');
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
    db.close();
  });
}

const readEntry = (req, res) => {
  const findKey = req.body.findKey;
  const value = req.body.value;
  if(!findKey || !value){
    res.status(400).send('Did not get input');
    return;
  }
  MongoClient.connect(MONGO_URL, (err, db) => {
    if(err){
      console.log('Could not connect to database');
      res.status(503).send('could not connect to db');
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
    db.close();
  });
}

const updateEntry = (req, res) => {
  const entry = req.body.entry;
  const updatedValue = req.body.updated;
  if(!entry || !updatedValue){
    res.status(400).send('Did not get input');
    return;
  }
  MongoClient.connect(MONGO_URL, (err, db) => {
    if(err){
      console.log('Could not connect to Database');
      res.status(503).send('could not connect to db');
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
    db.close();
  });
}

const deleteEntry = (req, res) => {
  const entry = req.body.entry;
  if(!entry){
    res.status(400).send('Did not get input');
    return;
  }
  MongoClient.connect(MONGO_URL, (err, db) => {
    if(err){
      console.log('Could not connect to Database');
      res.status(503).send('could not connect to db');
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
    db.close();
  });
}

server.listen(PORT, () => console.log(`listening in port ${PORT}`));
