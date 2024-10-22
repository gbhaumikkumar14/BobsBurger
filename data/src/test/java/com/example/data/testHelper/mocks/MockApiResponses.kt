package com.example.data.testHelper.mocks

object MockApiResponses {
    val getAllCharactersResponse = """[
  {
    "id": 1,
    "name": "\"Dottie Minerva\"",
    "relatives": [],
    "wikiUrl": "https://bobs-burgers.fandom.com/wiki/%22Dottie_Minerva%22",
    "image": "https://bobsburgers-api.herokuapp.com/images/characters/1.jpg",
    "gender": "Female",
    "hair": "Blonde",
    "occupation": "Student at Wagstaff School",
    "allOccupations": [
      "Student at Wagstaff School"
    ],
    "firstEpisode": "\"The Kids Run the Restaurant\"",
    "voicedBy": "Wendy Molyneux",
    "url": "https://bobsburgers-api.herokuapp.com/characters/1"
  },
  {
    "id": 2,
    "name": "Mabel \"Abby\" Haddington",
    "relatives": [],
    "wikiUrl": "https://bobs-burgers.fandom.com/wiki/Abby_Haddington",
    "image": "https://bobsburgers-api.herokuapp.com/images/characters/2.jpg",
    "gender": "Female",
    "hair": "Blonde",
    "age": "9-10",
    "occupation": "Student at Wagstaff School",
    "allOccupations": [
      "Student at Wagstaff School"
    ],
    "firstEpisode": "\"Slumber Party\"",
    "voicedBy": "Rachel Dratch",
    "url": "https://bobsburgers-api.herokuapp.com/characters/2"
  },
  {
    "allOccupations": [],
    "id": 3,
    "name": "Adam",
    "relatives": [
      {
        "_id": "66275ca214a11b6c0143b1e8",
        "name": "Unnamed wife"
      }
    ],
    "wikiUrl": "https://bobs-burgers.fandom.com/wiki/Adam",
    "image": "https://bobsburgers-api.herokuapp.com/images/characters/3.jpg",
    "gender": "Male",
    "hair": "Brown",
    "firstEpisode": "\"Mr. Lonely Farts\"",
    "voicedBy": "Brian Huskey",
    "url": "https://bobsburgers-api.herokuapp.com/characters/3"
  }
]"""

    val getCharacterDetailResponse = """{
  "id": 1,
  "name": "\"Dottie Minerva\"",
  "relatives": [],
  "wikiUrl": "https://bobs-burgers.fandom.com/wiki/%22Dottie_Minerva%22",
  "image": "https://bobsburgers-api.herokuapp.com/images/characters/1.jpg",
  "gender": "Female",
  "hair": "Blonde",
  "occupation": "Student at Wagstaff School",
  "allOccupations": [
    "Student at Wagstaff School"
  ],
  "firstEpisode": "\"The Kids Run the Restaurant\"",
  "voicedBy": "Wendy Molyneux",
  "url": "https://bobsburgers-api.herokuapp.com/characters/1"
}"""
}