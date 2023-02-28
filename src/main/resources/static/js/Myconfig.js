const MYNFT_ABI = [
	{
		"constant": false,
		"inputs": [
			{
				"name": "_repoAddress",
				"type": "address"
			},
			{
				"name": "_tokenId",
				"type": "uint256"
			},
			{
				"name": "_auctionTitle",
				"type": "string"
			},
			{
				"name": "_metadata",
				"type": "string"
			},
			{
				"name": "_price",
				"type": "uint256"
			},
			{
				"name": "_v",
				"type": "string"
			},
			{
				"name": "_r",
				"type": "string"
			},
			{
				"name": "_s",
				"type": "string"
			}
		],
		"name": "createAuction",
		"outputs": [
			{
				"name": "",
				"type": "bool"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_auctionId",
				"type": "uint256"
			}
		],
		"name": "getAuctionById",
		"outputs": [
			{
				"name": "name",
				"type": "string"
			},
			{
				"name": "price",
				"type": "uint256"
			},
			{
				"name": "metadata",
				"type": "string"
			},
			{
				"name": "tokenId",
				"type": "uint256"
			},
			{
				"name": "repoAddress",
				"type": "address"
			},
			{
				"name": "owner",
				"type": "address"
			},
			{
				"name": "v",
				"type": "string"
			},
			{
				"name": "r",
				"type": "string"
			},
			{
				"name": "s",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"name": "auctions",
		"outputs": [
			{
				"name": "name",
				"type": "string"
			},
			{
				"name": "price",
				"type": "uint256"
			},
			{
				"name": "metadata",
				"type": "string"
			},
			{
				"name": "tokenId",
				"type": "uint256"
			},
			{
				"name": "repoAddress",
				"type": "address"
			},
			{
				"name": "owner",
				"type": "address"
			},
			{
				"name": "v",
				"type": "string"
			},
			{
				"name": "r",
				"type": "string"
			},
			{
				"name": "s",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_auctionId",
				"type": "uint256"
			},
			{
				"name": "_to",
				"type": "address"
			},
			{
				"name": "_v",
				"type": "string"
			},
			{
				"name": "_r",
				"type": "string"
			},
			{
				"name": "_s",
				"type": "string"
			}
		],
		"name": "finalizeAuction",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [],
		"name": "getCount",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_owner",
				"type": "address"
			}
		],
		"name": "getAuctionsOf",
		"outputs": [
			{
				"name": "",
				"type": "uint256[]"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "",
				"type": "address"
			},
			{
				"name": "",
				"type": "uint256"
			}
		],
		"name": "auctionOwner",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_owner",
				"type": "address"
			}
		],
		"name": "getAuctionsCountOfOwner",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "fallback"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"name": "_owner",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_auctionId",
				"type": "uint256"
			}
		],
		"name": "AuctionCreated",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"name": "_owner",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_auctionId",
				"type": "uint256"
			}
		],
		"name": "AuctionFinalized",
		"type": "event"
	}
]

const HOUZZ_ABI = [
	{
		"constant": false,
		"inputs": [
			{
				"name": "_repoAddress",
				"type": "address"
			},
			{
				"name": "_tokenId",
				"type": "uint256"
			},
			{
				"name": "_auctionTitle",
				"type": "string"
			},
			{
				"name": "_metadata",
				"type": "string"
			},
			{
				"name": "_price",
				"type": "uint256"
			},
			{
				"name": "_v",
				"type": "string"
			},
			{
				"name": "_r",
				"type": "string"
			},
			{
				"name": "_s",
				"type": "string"
			}
		],
		"name": "createAuction",
		"outputs": [
			{
				"name": "",
				"type": "bool"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_auctionId",
				"type": "uint256"
			}
		],
		"name": "getAuctionById",
		"outputs": [
			{
				"name": "name",
				"type": "string"
			},
			{
				"name": "price",
				"type": "uint256"
			},
			{
				"name": "metadata",
				"type": "string"
			},
			{
				"name": "tokenId",
				"type": "uint256"
			},
			{
				"name": "repoAddress",
				"type": "address"
			},
			{
				"name": "owner",
				"type": "address"
			},
			{
				"name": "v",
				"type": "string"
			},
			{
				"name": "r",
				"type": "string"
			},
			{
				"name": "s",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"name": "auctions",
		"outputs": [
			{
				"name": "name",
				"type": "string"
			},
			{
				"name": "price",
				"type": "uint256"
			},
			{
				"name": "metadata",
				"type": "string"
			},
			{
				"name": "tokenId",
				"type": "uint256"
			},
			{
				"name": "repoAddress",
				"type": "address"
			},
			{
				"name": "owner",
				"type": "address"
			},
			{
				"name": "v",
				"type": "string"
			},
			{
				"name": "r",
				"type": "string"
			},
			{
				"name": "s",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_auctionId",
				"type": "uint256"
			},
			{
				"name": "_to",
				"type": "address"
			},
			{
				"name": "_v",
				"type": "string"
			},
			{
				"name": "_r",
				"type": "string"
			},
			{
				"name": "_s",
				"type": "string"
			}
		],
		"name": "finalizeAuction",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [],
		"name": "getCount",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_owner",
				"type": "address"
			}
		],
		"name": "getAuctionsOf",
		"outputs": [
			{
				"name": "",
				"type": "uint256[]"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "",
				"type": "address"
			},
			{
				"name": "",
				"type": "uint256"
			}
		],
		"name": "auctionOwner",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_owner",
				"type": "address"
			}
		],
		"name": "getAuctionsCountOfOwner",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "fallback"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"name": "_owner",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_auctionId",
				"type": "uint256"
			}
		],
		"name": "AuctionCreated",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"name": "_owner",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_auctionId",
				"type": "uint256"
			}
		],
		"name": "AuctionFinalized",
		"type": "event"
	}
]


const GAS_AMOUNT=500000;

const MYNFT_CA = "0xd61B3da16A1EE7Abf51bBc7beda3AB4906b1CB36";
const HOUZZ_CA = "0xf1880E0A391D2C069f4755E82e04D19b1e7e08CD";

