import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container'
import Paper from '@mui/material/Paper'
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import UpdateIcon from '@mui/icons-material/Update';


export default function Customer() {
    const paperstyle={padding:'50px 20px', width:800, margin:'20px auto'}
    const [id, setId] = React.useState('')
    const [firstname, setFirstname] = React.useState('')
    const [lastname, setLastname] = React.useState('')
    const [customers, setCustomers] = React.useState([])

    const postFunctionClick=(e)=>{
        e.preventDefault()
        const customer = {id,firstname,lastname}
        //console.log(customer)
        fetch("http://localhost:8080/customer/",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(customer)
        }).then(()=>{
            console.log("New Customer added")
        })
    }
    React.useEffect(()=>{
        fetch("http://localhost:8080/customer/")
        .then(res=>res.json())
        .then((result)=>{
            setCustomers(result)
        })

    })
    
  return (
    <Container>
        <Paper elevation={3} style={paperstyle}>
            <h1 style={{color:"blue"}}>Add Customer</h1>
    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1},
      }}
      noValidate
      autoComplete="off"
    >
      <TextField id="outlined-basic" type="number" label="Customer ID" variant="standard" fullWidth
      value={id}
      onChange = {(e)=> setId(e.target.value)}
      />
      <TextField id="filled-basic" label="Customer Firstname" variant="filled" fullWidth 
      value={firstname}
      onChange = {(e) => setFirstname(e.target.value)}
        />
      <TextField id="standard-basic" label="Customer Lastname" variant="outlined" fullWidth
      value={lastname}
      onChange = {(e)=> setLastname(e.target.value)}
      />
      <Button variant="contained" color="secondary" onClick={postFunctionClick}>Submit</Button>
    </Box>
    </Paper>
    <Paper elevation={3} style={paperstyle}>
        {customers.map(customer=>(
            <Paper elevation={6} style={{margin:"10px",padding:'15px', textAlign:'left'}} key={customer.id}>
                Id: {customer.id}  	&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                
                <Button variant="outlined" startIcon={<UpdateIcon />} onClick={(e)=>{
                    e.preventDefault()
                    const iD=customer.id;
                    const newCustomer = {iD,firstname,lastname}
                    fetch("http://localhost:8080/customer/"+customer.id,{
                        method:"PUT",
                        headers:{"Content-Type":"application/json"},
                        body:JSON.stringify(newCustomer)
                    })
                    .then(()=>{
                        console.log("Customer "+ customer.id + " updated")})}}>
                    Update
                </Button> &nbsp; &nbsp;&nbsp;&nbsp;
                
                <Button variant="outlined" startIcon={<DeleteIcon />} onClick={(e)=>{
                    e.preventDefault()
                    fetch("http://localhost:8080/customer/"+customer.id,{method:"DELETE"})
                    .then(()=>{
                        console.log("Customer "+ customer.id + " deleted")})}}>
                    Delete
                </Button> <br/>
                Firstname: {customer.firstname}<br/>
                Lastname: {customer.lastname}<br/>
            </Paper>
        ))}

    </Paper>
    </Container>
  );
}
