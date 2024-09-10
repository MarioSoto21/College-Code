// Mario Soto


const api_url = 'https://www.randyconnolly.com/funwebdev/3rd/api/stocks/companies.php'
        async function getCompany(){
            const response = await fetch(api_url);
            const data = await response.json();
            // console.log(data);
            // console.log(data.symbol);
            // console.log(data.name);

            // We can deconstruct the object
            const {symbol, name} = data;

            // document.getElementById('symbol').textContent = symbol;
            // document.getElementById('name').textContent = name;
            // console.log(symbol);
            // console.log(name);
            const selTrack = document.getElementById("sel");
            for(option of data){
                const newOption = document.createElement("option")
                newOption.value = option.symbol;
                newOption.text = option.name;
                selTrack.appendChild(newOption)
            }


        }

        getCompany();