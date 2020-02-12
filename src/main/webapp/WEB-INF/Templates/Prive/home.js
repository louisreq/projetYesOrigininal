import { select, arc } from 'd3';

const svg = select('svg');

const width = parseFloat(svg.attr('width'));
const height = +svg.attr('height');

const g = svg.append('g')
    .attr('transform',`translate(${width / 2},${height / 2})`);

const circle = g.append('circle')
    .attr('r', height / 2 - 10)
    .attr('fill', '#f7d52a')
    .attr('stroke','black')
    .attr('stroke-width','5');

const eyeSpacing = 100;
const eyeYoffset = -70;
const eyeRadius = 30;
const eyebrowWidth = 70;
const eyebrowHeight = 15;
const eyebrowYoffset = -70;

const eyesG = g
    .append('g')
    .attr('transform',`translate(0, ${eyeYoffset})`);

const LeftEye = eyesG
    .append('circle')
    .attr('r', eyeRadius)
    .attr('cx', -eyeSpacing);

const RightEye = eyesG
    .append('circle')
    .attr('r', eyeRadius)
    .attr('cx', eyeSpacing);

const leftEyebrow = eyesG
    .append('rect')
    .attr('x', -eyeSpacing - eyebrowWidth / 2)
    .attr('y', eyebrowYoffset)
    .attr('width', eyebrowWidth)
    .attr('height', eyebrowHeight);

const rightEyebrow = eyesG
    .append('rect')
    .attr('x', eyeSpacing - eyebrowWidth / 2)
    .attr('y', eyebrowYoffset)
    .attr('width', eyebrowWidth)
    .attr('height', eyebrowHeight)
    .transition().duration(2000)
    .attr('y', eyebrowYoffset - 40)
    .transition().duration(2000)
    .attr('y', eyebrowYoffset)
;

const mouth = g
    .append('path')
    .attr('d', arc()({
        innerRadius: 0,
        outerRadius: 130,
        startAngle: Math.PI / 2,
        endAngle: Math.PI * 3 / 2
    }))
;
