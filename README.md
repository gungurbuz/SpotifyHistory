# Spotify Streaming History Analyzer

This Java application parses your Spotify extended streaming history to provide insights such as the most played song by playtime and play count.

## Overview

This program analyzes your Spotify extended streaming history. It extracts data such as track name, album, artist, and playtime. It then identifies the most played song based on total playtime and play count.

## Features

- Parses JSON file containing Spotify listening history.
- Identifies the most played song by playtime and play count.
- Displays insights such as song name, artist, total playtime, and play count.

### To Do
- [ ] Convert to binary tree
- [ ] Allow user to input file
- [ ] Implement GUI


## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your system.
- Spotify Extended Streaming History (download from spotify account).

### Installation

1. Clone this repository to your local machine.
2. Compile the Java files using the Java compiler:
`javac MainTest.java Song.java`

### Usage

1. Replace `history.json` with your own JSON file containing music listening history (combine streaming histories if they are in two different files).
2. Run the compiled `MainTest` class:
`java MainTest`
