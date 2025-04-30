// Mario Soto


const queryString = window.location.search
// console.log(queryString);

const urlParams = new URLSearchParams(queryString);
const sym = urlParams.get('symbol')
// console.log(sym)

const api_url = 
"https://www.randyconnolly.com/funwebdev/3rd/api/stocks/history.php"+queryString;
// console.log(api_url)

    async function getCompany(){
        const response = await fetch(api_url);
        const data = await response.json();
        console.log(data);
        // getapi(api_url);
        show(data);
    }
    getCompany()
    function show(data) {
        let tab = 
            `<tr>
            <th>date</th>
            <th>open</th>
            <th>high</th>
            <th>low</th>
            <th>close</th>
            <th>volume</th>
            </tr>`;
        
        // Loop to access all rows 
        for (let r of data) {
            tab += `<tr> 
        <td>${r.date} </td>
        <td>${r.open}</td>
        <td>${r.high}</td> 
        <td>${r.low}</td>
        <td>${r.close}</td>
        <td>${r.volume}</td>          
    </tr>`;
        }
        // Setting innerHTML as tab variable
        document.getElementById("header").innerHTML= sym;
        document.getElementById("info").innerHTML = tab;
    }